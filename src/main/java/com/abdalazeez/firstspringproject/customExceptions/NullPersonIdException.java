package com.abdalazeez.firstspringproject.customExceptions;

public class NullPersonIdException extends Throwable {
    public NullPersonIdException(String message) {
        super(message);
    }
}
