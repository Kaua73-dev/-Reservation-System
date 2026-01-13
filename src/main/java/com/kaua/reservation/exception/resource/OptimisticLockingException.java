package com.kaua.reservation.exception.resource;

public class OptimisticLockingException extends RuntimeException {
    public OptimisticLockingException() {
        super("The feature has already been updated by the user.");
    }
}
