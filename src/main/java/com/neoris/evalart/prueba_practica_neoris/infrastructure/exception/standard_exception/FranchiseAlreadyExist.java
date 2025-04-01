package com.neoris.evalart.prueba_practica_neoris.infrastructure.exception.standard_exception;

import org.springframework.http.HttpStatus;

public class FranchiseAlreadyExist extends StandardException {
    public FranchiseAlreadyExist() {
        super("E001", HttpStatus.BAD_REQUEST, "Can't create Franchise with information provided");
    }
}
