package com.neoris.evalart.prueba_practica_neoris.infrastructure.exception.standard_exception;

import com.neoris.evalart.prueba_practica_neoris.infrastructure.exception.StandardException;
import org.springframework.http.HttpStatus;

public class FranchiseNotExist extends StandardException {
    public FranchiseNotExist() {
        super("E003", HttpStatus.BAD_REQUEST, "Can't find Franchise with information provided");
    }
}
