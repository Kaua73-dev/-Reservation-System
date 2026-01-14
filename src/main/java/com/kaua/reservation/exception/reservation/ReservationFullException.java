package com.kaua.reservation.exception.reservation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ReservationFullException extends RuntimeException {
    public ReservationFullException() {
        super("Reserved full");
    }
}
