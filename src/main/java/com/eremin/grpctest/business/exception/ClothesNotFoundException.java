package com.eremin.grpctest.business.exception;

public class ClothesNotFoundException extends RuntimeException {

    public ClothesNotFoundException(String message) {
        super(message);
    }
}
