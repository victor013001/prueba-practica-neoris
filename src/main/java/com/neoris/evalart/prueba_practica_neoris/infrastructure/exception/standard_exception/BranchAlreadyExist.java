package com.neoris.evalart.prueba_practica_neoris.infrastructure.exception.standard_exception;

import com.neoris.evalart.prueba_practica_neoris.infrastructure.exception.StandardException;
import org.springframework.http.HttpStatus;

public class BranchAlreadyExist extends StandardException {
    public BranchAlreadyExist() {
        super("E002", HttpStatus.BAD_REQUEST, "Can't create Branch with information provided");
    }
}
