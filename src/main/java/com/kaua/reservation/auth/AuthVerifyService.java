package com.kaua.reservation.auth;


import com.kaua.reservation.entity.model.User;
import org.springframework.security.core.context.SecurityContextHolder;

public abstract class AuthVerifyService {

    protected User getAuthenticatedUser(){
        return (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }


}
