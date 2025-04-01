package com.neoris.evalart.prueba_practica_neoris.application.service.impl;

import com.neoris.evalart.prueba_practica_neoris.application.mapper.FranchiseMapper;
import com.neoris.evalart.prueba_practica_neoris.application.mapper.FranchiseMapperImpl;
import com.neoris.evalart.prueba_practica_neoris.domain.model.Franchise;
import com.neoris.evalart.prueba_practica_neoris.infrastructure.dto.FranchiseDto;
import com.neoris.evalart.prueba_practica_neoris.infrastructure.exception.standard_exception.FranchiseAlreadyExist;
import com.neoris.evalart.prueba_practica_neoris.infrastructure.repository.FranchiseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static com.neoris.evalart.prueba_practica_neoris.util.FranchiseDtoDataUtil.getFranchiseDtoWithUuidNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FranchiseServiceImplTest {
    @InjectMocks
    private FranchiseServiceImpl franchiseService;

    @Mock
    private FranchiseRepository franchiseRepository;

    @Spy
    private FranchiseMapper franchiseMapper = new FranchiseMapperImpl();

    @Test
    void testCreateFranchise() {
        FranchiseDto franchiseDto = getFranchiseDtoWithUuidNull();
        Franchise franchise = franchiseMapper.toFranchise(franchiseDto);
        when(franchiseRepository.existsByName(anyString())).thenReturn(Mono.just(false));
        when(franchiseRepository.save(any(Franchise.class))).thenReturn(Mono.just(franchise));

        Mono<FranchiseDto> result = franchiseService.createFranchise(franchiseDto);

        StepVerifier.create(result)
                .consumeNextWith(franchiseDto1 -> {
                    assertEquals(franchise.getUuid(), franchiseDto1.uuid());
                })
                .verifyComplete();
        verify(franchiseRepository).existsByName(franchiseDto.name());
        verify(franchiseRepository).save(any(Franchise.class));
    }

    @Test
    void testCreateFranchise_whenFranchiseAlreadyExists() {
        FranchiseDto franchiseDto = getFranchiseDtoWithUuidNull();
        when(franchiseRepository.existsByName(anyString())).thenReturn(Mono.just(true));

        Mono<FranchiseDto> result = franchiseService.createFranchise(franchiseDto);

        StepVerifier.create(result)
                .expectError(FranchiseAlreadyExist.class)
                .verify();
        verify(franchiseRepository).existsByName(franchiseDto.name());
        verify(franchiseRepository, never()).save(any());
    }
}