package com.neoris.evalart.prueba_practica_neoris.infrastructure.exception.standard_exception;

import com.neoris.evalart.prueba_practica_neoris.infrastructure.exception.StandardException;
import org.springframework.http.HttpStatus;

public class ProductAlreadyExist extends StandardException {
    public ProductAlreadyExist() {
        super("E005", HttpStatus.BAD_REQUEST, "Can't create Product with information provided");
    }
}
