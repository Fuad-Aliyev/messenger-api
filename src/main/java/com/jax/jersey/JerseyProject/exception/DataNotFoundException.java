package com.jax.jersey.JerseyProject.exception;

import java.io.Serializable;

public class DataNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -6028497726865186890L;

    public DataNotFoundException(String message) {
        super(message);
    }
}
