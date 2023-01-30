package com.evo.apatrios.exception;

import lombok.Data;

import java.util.UUID;

@Data
public class AwardNotFoundException extends RuntimeException {

    private final String errorMessage;
    private final UUID id;

    @Override
    public String getMessage() {
        return errorMessage + " - " + id;
    }
}
