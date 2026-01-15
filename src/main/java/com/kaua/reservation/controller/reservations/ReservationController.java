package com.kaua.reservation.controller.reservations;


import com.kaua.reservation.dto.response.ReservationResponse;
import com.kaua.reservation.entity.model.User;
import com.kaua.reservation.service.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class ReservationController {

    private final ReservationService reservationService;


    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }


    @PostMapping("/reservation/{resourceId}")
    public ResponseEntity<ReservationResponse> createReservation(@PathVariable Integer resourceId){
        ReservationResponse response = reservationService.createReservation(resourceId);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @PostMapping("/reservation/confirm/{reservationId}")
    public ReservationResponse confirmReservation(@PathVariable Integer reservationId){
        return reservationService.confirmReservation(reservationId);
    }


    @GetMapping("/reservation")
    public List<ReservationResponse> getAllReservationByUser(){
        return reservationService.getAllReservations();
    }

    @DeleteMapping("/reservation/{reservationId}")
    public void cancelReservationByUserAndId(@PathVariable Integer reservationId){
        reservationService.cancelReservationById(reservationId);
    }


}
