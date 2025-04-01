package com.neoris.evalart.prueba_practica_neoris.infrastructure.exception.standard_exception;

import com.neoris.evalart.prueba_practica_neoris.infrastructure.exception.StandardException;
import org.springframework.http.HttpStatus;

public class NothingToChange extends StandardException {
    public NothingToChange() {
        super("E007", HttpStatus.NOT_MODIFIED, "Nothing to change with information provided");
    }
}
