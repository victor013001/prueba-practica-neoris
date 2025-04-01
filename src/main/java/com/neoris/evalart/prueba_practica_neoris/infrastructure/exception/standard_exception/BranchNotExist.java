package com.neoris.evalart.prueba_practica_neoris.infrastructure.exception.standard_exception;

import com.neoris.evalart.prueba_practica_neoris.infrastructure.exception.StandardException;
import org.springframework.http.HttpStatus;

public class BranchNotExist extends StandardException {
    public BranchNotExist() {
        super("E004", HttpStatus.BAD_REQUEST, "Can't find Branch with information provided");
    }
}
