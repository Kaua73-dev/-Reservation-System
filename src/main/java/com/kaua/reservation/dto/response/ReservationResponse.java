package com.kaua.reservation.dto.response;

import com.kaua.reservation.entity.enums.ReservationStatus;

import java.time.LocalDateTime;

public record ReservationResponse(LocalDateTime expires_at, ReservationStatus status) {
}
