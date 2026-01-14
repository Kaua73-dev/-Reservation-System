package com.kaua.reservation.service;

import com.kaua.reservation.auth.AuthVerifyService;
import com.kaua.reservation.entity.repository.ReservationRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ReservationService extends AuthVerifyService {

    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }






}
