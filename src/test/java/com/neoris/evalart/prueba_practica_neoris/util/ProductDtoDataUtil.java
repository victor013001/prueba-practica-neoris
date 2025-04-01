package com.neoris.evalart.prueba_practica_neoris.util;
;
import com.neoris.evalart.prueba_practica_neoris.infrastructure.dto.ProductDto;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class ProductDtoDataUtil {
    private ProductDtoDataUtil() throws InstantiationException {
        throw new InstantiationException("Data class cannot be instantiated");
    }

    public static ProductDto getProductDto() {
        var randomId = ThreadLocalRandom.current().nextLong();
        return new ProductDto(UUID.randomUUID().toString(), "Product" + randomId, ThreadLocalRandom.current().nextInt());
    }

    public static ProductDto getProductDtoWithUuidNull() {
        var randomId = ThreadLocalRandom.current().nextLong();
        return new ProductDto(null,"Product" + randomId, ThreadLocalRandom.current().nextInt());
    }
}
