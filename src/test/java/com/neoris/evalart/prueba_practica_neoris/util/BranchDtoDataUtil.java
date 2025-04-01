package com.neoris.evalart.prueba_practica_neoris.util;

import com.neoris.evalart.prueba_practica_neoris.infrastructure.dto.BranchDto;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import static com.neoris.evalart.prueba_practica_neoris.util.ProductDtoDataUtil.getProductDto;

public class BranchDtoDataUtil {
    private BranchDtoDataUtil() throws InstantiationException {
        throw new InstantiationException("Data class cannot be instantiated");
    }

    public static BranchDto getBranchDto() {
        var randomId = ThreadLocalRandom.current().nextLong();
        return new BranchDto(UUID.randomUUID().toString(), "Branch" + randomId, List.of(getProductDto()));
    }

    public static BranchDto getBranchDtoWithUuidNull() {
        var randomId = ThreadLocalRandom.current().nextLong();
        return new BranchDto(null,"Branch" + randomId, List.of(getProductDto()));
    }
}
