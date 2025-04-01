package com.neoris.evalart.prueba_practica_neoris.infrastructure.exception.standard_exception;

import com.neoris.evalart.prueba_practica_neoris.infrastructure.exception.StandardException;
import org.springframework.http.HttpStatus;

public class ProductNotExist extends StandardException {
    public ProductNotExist() {
        super("E006", HttpStatus.BAD_REQUEST, "Can't find Product with information provided");
    }
}
