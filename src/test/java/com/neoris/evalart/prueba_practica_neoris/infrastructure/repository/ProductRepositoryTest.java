package com.neoris.evalart.prueba_practica_neoris.infrastructure.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static com.neoris.evalart.prueba_practica_neoris.util.ProductDataUtil.getProduct;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {
    @Mock
    private ProductRepository productRepository;

    @Test
    void getAllBranches() {
        Mockito.when(productRepository.findAll())
                .thenReturn(Flux.just(
                        getProduct(),
                        getProduct()
                ));

        StepVerifier.create(productRepository.findAll())
                .expectNextCount(2)
                .verifyComplete();
    }
}