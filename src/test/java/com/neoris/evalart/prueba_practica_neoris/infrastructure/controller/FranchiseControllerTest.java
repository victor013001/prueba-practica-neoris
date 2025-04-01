package com.neoris.evalart.prueba_practica_neoris.infrastructure.controller;

import com.neoris.evalart.prueba_practica_neoris.application.mapper.FranchiseMapper;
import com.neoris.evalart.prueba_practica_neoris.application.mapper.FranchiseMapperImpl;
import com.neoris.evalart.prueba_practica_neoris.application.service.FranchiseService;
import com.neoris.evalart.prueba_practica_neoris.infrastructure.dto.FranchiseDto;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.UUID;

import static com.neoris.evalart.prueba_practica_neoris.util.FranchiseDtoDataUtil.getFranchiseDtoWithUuidAndBranchesNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@AutoConfigureWebTestClient
@WebFluxTest(controllers = FranchiseController.class)
class FranchiseControllerTest {

    private final String BASE_URL = "/api/v1/franchise";

    @MockitoBean
    private FranchiseService franchiseService;
    @MockitoBean
    private FranchiseMapper franchiseMapper = new FranchiseMapperImpl();

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void GivenValidFranchise_WhenCreateFranchise_ThenReturnMonoFranchiseDto() {
        FranchiseDto franchiseDto = getFranchiseDtoWithUuidAndBranchesNull();
        FranchiseDto franchiseDtoWithUuid =
                new FranchiseDto(UUID.randomUUID().toString(), franchiseDto.name(), null);
        Mockito.when(franchiseService.createFranchise(any(FranchiseDto.class)))
                .thenReturn(Mono.just(franchiseDtoWithUuid));

        webTestClient.post()
                .uri(BASE_URL)
                .bodyValue(franchiseDto)
                .exchange()
                .expectStatus()
                .isCreated()
                .expectBody(FranchiseDto.class)
                .consumeWith(response -> {
                    FranchiseDto responseBody = response.getResponseBody();
                    assertNotNull(responseBody);
                    assertEquals(franchiseDto.name(), responseBody.name());
                });
        Mockito.verify(franchiseService).createFranchise(any(FranchiseDto.class));
    }
}