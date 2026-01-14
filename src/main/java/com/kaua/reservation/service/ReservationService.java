package com.kaua.reservation.service;

import com.kaua.reservation.auth.AuthVerifyService;
import com.kaua.reservation.dto.response.ReservationResponse;
import com.kaua.reservation.entity.enums.ReservationStatus;
import com.kaua.reservation.entity.enums.ResourceStatus;
import com.kaua.reservation.entity.model.Reservation;
import com.kaua.reservation.entity.model.Resource;
import com.kaua.reservation.entity.model.User;
import com.kaua.reservation.entity.repository.ReservationRepository;
import com.kaua.reservation.entity.repository.ResourceRepository;
import com.kaua.reservation.exception.reservation.InvalidReservationStateException;
import com.kaua.reservation.exception.reservation.ReservationExpiredException;
import com.kaua.reservation.exception.reservation.ReservationFullException;
import com.kaua.reservation.exception.resource.ResourceNotFoundException;
import com.kaua.reservation.exception.user.UserNoFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Repository
public class ReservationService extends AuthVerifyService {

    private final ReservationRepository reservationRepository;
    private final ResourceRepository resourceRepository;

    public ReservationService(ReservationRepository reservationRepository, ResourceRepository resourceRepository) {
        this.reservationRepository = reservationRepository;
        this.resourceRepository = resourceRepository;
    }


    private ReservationResponse toResponse(Reservation r){
        return new ReservationResponse(
                r.getExpires_at(),
                r.getStatus()
        );
    }

    private void updateResourceAvailability(Resource resource){
        if(resource.getReservedCount() < resource.getCapacity()){
            resource.setStatus(ResourceStatus.AVAILABLE);
        } else{
            resource.setStatus(ResourceStatus.UNAVAILABLE);
        }

    }

    private void expireReservation(Reservation reservation){
        reservation.setStatus(ReservationStatus.EXPIRED);

        Resource resource = reservation.getResource();
        resource.setReservedCount(resource.getReservedCount() - 1);
        updateResourceAvailability(resource);
    }


    public ReservationResponse createReservation(Integer resourceId){
        User user = getAuthenticatedUser();

        Resource resource = resourceRepository.findById(resourceId)
                .orElseThrow(() ->
                        new ResourceNotFoundException()
                        );

        if(resource.getReservedCount() >= resource.getCapacity()){
            throw new ReservationFullException();
        }

        Reservation reservation = new Reservation();
        reservation.setUser(user);
        reservation.setResource(resource);
        reservation.setStatus(ReservationStatus.RESERVED);
        reservation.setExpires_at(LocalDateTime.now().plusMinutes(15));

        resource.setReservedCount(resource.getReservedCount() + 1);
        updateResourceAvailability(resource);


        resourceRepository.save(resource);
        return toResponse(reservationRepository.save(reservation));
    }

    @Transactional
    public Reservation confirmReservation(Integer reservationId){
            User user = getAuthenticatedUser();

            Reservation reservation = reservationRepository.findById(reservationId).orElseThrow(() ->
                    new ReservationFullException()
                    );

            if(!reservation.getUser().getId().equals(user.getId())){
                throw new UserNoFoundException();
            }

            if(reservation.getStatus() != ReservationStatus.RESERVED){
                throw new InvalidReservationStateException();
            }

            if(reservation.getExpires_at().isBefore(LocalDateTime.now())){
                expireReservation(reservation);
                throw new ReservationExpiredException();
            }

            reservation.setStatus(ReservationStatus.CONFIRMED);
           return  reservationRepository.save(reservation);

    }



}
