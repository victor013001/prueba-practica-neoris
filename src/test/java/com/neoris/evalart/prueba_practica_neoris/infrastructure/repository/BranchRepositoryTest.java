package com.neoris.evalart.prueba_practica_neoris.infrastructure.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static com.neoris.evalart.prueba_practica_neoris.util.BranchDataUtil.getBranch;
import static com.neoris.evalart.prueba_practica_neoris.util.BranchDataUtil.getBranchWithProducts;

@ExtendWith(MockitoExtension.class)
class BranchRepositoryTest {
    @Mock
    private BranchRepository branchRepository;

    @Test
    void getAllBranches() {
        Mockito.when(branchRepository.findAll())
                .thenReturn(Flux.just(
                        getBranch(),
                        getBranchWithProducts()
                ));

        StepVerifier.create(branchRepository.findAll())
                .expectNextCount(2)
                .verifyComplete();
    }
}