package com.devsuperior.bds02.services.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    private static final long seriaLVersionUID = 1L;

    public ResourceNotFoundException(String msg) {
        super(msg);
    }
}