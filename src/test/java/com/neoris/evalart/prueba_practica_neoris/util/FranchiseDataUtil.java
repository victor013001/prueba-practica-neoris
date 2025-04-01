package com.neoris.evalart.prueba_practica_neoris.util;

import com.neoris.evalart.prueba_practica_neoris.domain.model.Franchise;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import static com.neoris.evalart.prueba_practica_neoris.util.BranchDataUtil.getBranch;
import static com.neoris.evalart.prueba_practica_neoris.util.BranchDataUtil.getBranchWithProducts;

public class FranchiseDataUtil {
    private FranchiseDataUtil() throws InstantiationException {
        throw new InstantiationException("Data class cannot be instantiated");
    }

    public static Franchise getFranchise() {
        var randomId = ThreadLocalRandom.current().nextLong();
        return Franchise.builder()
                .id(randomId)
                .uuid(UUID.randomUUID().toString())
                .name("Franchise " + randomId)
                .build();
    }

    public static Franchise getFranchiseWithBranches() {
        var franchise = getFranchise();
        franchise.setBranches(List.of(getBranch(), getBranchWithProducts()));
        return franchise;
    }
}
