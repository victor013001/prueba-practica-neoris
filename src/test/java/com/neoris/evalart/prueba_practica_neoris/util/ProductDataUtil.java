package com.neoris.evalart.prueba_practica_neoris.util;

import com.neoris.evalart.prueba_practica_neoris.domain.model.Product;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class ProductDataUtil {
    private ProductDataUtil() throws InstantiationException {
        throw new InstantiationException("Data class cannot be instantiated");
    }

    public static Product getProduct() {
        var randomId = ThreadLocalRandom.current().nextLong();
        return Product.builder()
                .id(randomId)
                .uuid(UUID.randomUUID().toString())
                .name("Product " + randomId)
                .stock(ThreadLocalRandom.current().nextInt())
                .build();
    }
}
