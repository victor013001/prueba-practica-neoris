package com.neoris.evalart.prueba_practica_neoris.infrastructure.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static com.neoris.evalart.prueba_practica_neoris.util.FranchiseDataUtil.getFranchise;
import static com.neoris.evalart.prueba_practica_neoris.util.FranchiseDataUtil.getFranchiseWithBranches;

@ExtendWith(MockitoExtension.class)
class FranchiseRepositoryTest {
    @Mock
    private FranchiseRepository franchiseRepository;

    @Test
    void getAllBranches() {
        Mockito.when(franchiseRepository.findAll())
                .thenReturn(Flux.just(
                        getFranchise(),
                        getFranchiseWithBranches()
                ));

        StepVerifier.create(franchiseRepository.findAll())
                .expectNextCount(2)
                .verifyComplete();
    }
}