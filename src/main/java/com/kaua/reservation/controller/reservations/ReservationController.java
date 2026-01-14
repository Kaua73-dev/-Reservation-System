package com.kaua.reservation.controller.reservations;


import com.kaua.reservation.dto.response.ReservationResponse;
import com.kaua.reservation.service.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
