package com.kaua.reservation.dto.request;

import com.kaua.reservation.entity.enums.ResourceStatus;

public record ResourceRequest(String name, String category, int capacity, ResourceStatus status) {
}
