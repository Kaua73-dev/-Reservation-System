package com.kaua.reservation.dto.response;

import com.kaua.reservation.entity.enums.ResourceStatus;

public record ResourceResponse(String name, String category, int capacity, ResourceStatus status, Long version, Integer id) {
}
