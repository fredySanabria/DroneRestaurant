package com.s4n.restaurant.exception;

public class InstructionFileNotFoundException extends RuntimeException {
    public InstructionFileNotFoundException(String message) {
        super(message);
    }
}
