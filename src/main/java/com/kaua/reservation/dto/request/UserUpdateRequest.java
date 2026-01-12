package com.kaua.reservation.dto.request;

public record UserUpdateRequest(String name, String email, String cpf, String password) {
}
