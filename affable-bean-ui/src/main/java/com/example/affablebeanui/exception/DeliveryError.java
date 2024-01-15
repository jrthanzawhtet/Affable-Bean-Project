package com.example.affablebeanui.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class DeliveryError extends ResponseStatusException {
    public DeliveryError(){
        super(HttpStatus.BAD_REQUEST,"Delivery Error");
    }
}
