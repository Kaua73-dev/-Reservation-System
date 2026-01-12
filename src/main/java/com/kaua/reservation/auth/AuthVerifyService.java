package com.kaua.reservation.auth;


import com.kaua.reservation.dto.request.RegisterRequest;
import com.kaua.reservation.dto.response.ResourceResponse;
import com.kaua.reservation.entity.model.User;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;

public abstract class AuthVerifyService {

    protected User getAuthenticatedUser(){
        return (User) Objects.requireNonNull(SecurityContextHolder
                        .getContext()
                        .getAuthentication())
                        .getPrincipal();
    }

}
