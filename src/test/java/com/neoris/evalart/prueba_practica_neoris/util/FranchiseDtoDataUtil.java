package com.neoris.evalart.prueba_practica_neoris.util;

import com.neoris.evalart.prueba_practica_neoris.infrastructure.dto.FranchiseDto;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import static com.neoris.evalart.prueba_practica_neoris.util.BranchDtoDataUtil.getBranchDto;

public class FranchiseDtoDataUtil {
    private FranchiseDtoDataUtil() throws InstantiationException {
        throw new InstantiationException("Data class cannot be instantiated");
    }

    public static FranchiseDto getFranchiseDto() {
        var randomId = ThreadLocalRandom.current().nextLong();
        return new FranchiseDto(UUID.randomUUID().toString(), "Franchise" + randomId, List.of(getBranchDto()));
    }

    public static FranchiseDto getFranchiseDtoWithUuidNull() {
        var randomId = ThreadLocalRandom.current().nextLong();
        return new FranchiseDto(null,"Franchise" + randomId, List.of(getBranchDto()));
    }

    public static FranchiseDto getFranchiseDtoWithUuidAndBranchesNull() {
        var randomId = ThreadLocalRandom.current().nextLong();
        return new FranchiseDto(null,"Franchise" + randomId, null);
    }
}
