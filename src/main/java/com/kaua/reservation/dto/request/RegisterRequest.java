package com.kaua.reservation.dto.request;

public record RegisterRequest(String name, String email, String cpf, String password) {
}
