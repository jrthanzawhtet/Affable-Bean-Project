package com.example.paymentgateway.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidCardException extends ResponseStatusException {
    public InvalidCardException(){
        super(HttpStatus.BAD_REQUEST,"Invalid card number!");
    }
}
