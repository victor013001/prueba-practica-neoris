package com.neoris.evalart.prueba_practica_neoris.util;

import com.neoris.evalart.prueba_practica_neoris.domain.model.Branch;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import static com.neoris.evalart.prueba_practica_neoris.util.ProductDataUtil.getProduct;

public class BranchDataUtil {
    private BranchDataUtil() throws InstantiationException {
        throw new InstantiationException("Data class cannot be instantiated");
    }

    public static Branch getBranch() {
        var randomId = ThreadLocalRandom.current().nextLong();
        return Branch.builder()
                .id(randomId)
                .uuid(UUID.randomUUID())
                .name("Branch " + randomId)
                .build();
    }

    public static Branch getBranchWithProducts() {
        var branch = getBranch();
        branch.setProducts(List.of(getProduct(), getProduct()));
        return branch;
    }
}
