package com.abdalazeez.firstspringproject.customExceptions;

public class PersonNotFoundException extends Throwable {
    public PersonNotFoundException(String message) {
        super(message);
    }
}
