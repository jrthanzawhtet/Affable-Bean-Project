package com.example.affablebeanui.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class PaymentError extends ResponseStatusException {
    public PaymentError(){
        super(HttpStatus.BAD_REQUEST,"Payment Error!");
    }
}
