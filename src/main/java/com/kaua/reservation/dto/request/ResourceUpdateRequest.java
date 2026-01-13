package com.kaua.reservation.dto.request;

import com.kaua.reservation.entity.enums.ResourceStatus;
import org.antlr.v4.runtime.misc.NotNull;

public record ResourceUpdateRequest(String name, String category, Integer capacity, ResourceStatus status, @NotNull Long version) {
}
