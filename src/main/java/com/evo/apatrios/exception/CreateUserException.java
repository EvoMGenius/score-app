package com.evo.apatrios.exception;

import lombok.Data;

@Data
public class CreateUserException extends RuntimeException {

    private final String errorMessage;
}
