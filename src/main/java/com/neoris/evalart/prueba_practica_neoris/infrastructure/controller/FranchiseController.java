package com.neoris.evalart.prueba_practica_neoris.infrastructure.controller;

import com.neoris.evalart.prueba_practica_neoris.application.service.FranchiseService;
import com.neoris.evalart.prueba_practica_neoris.infrastructure.dto.FranchiseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/franchise")
public class FranchiseController {
    private final String LOG_PREFIX = "[Franchise_Controller_V1] >>> ";

    private final FranchiseService franchiseService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<FranchiseDto> createFranchise(@Valid @RequestBody final FranchiseDto franchiseRequest) {
        log.info("{} Creating Franchise with Name {}", LOG_PREFIX, franchiseRequest.name());
        return franchiseService.createFranchise(franchiseRequest);
    }

    @PatchMapping("/{franchiseUuid}")
    public Mono<Void> updateBranch(@Valid @RequestBody final FranchiseDto franchiseRequest, @PathVariable final String franchiseUuid) {
        log.info("{} Updating Branch UUID {} name", LOG_PREFIX, franchiseUuid);
        return franchiseService.updateFranchiseName(franchiseUuid, franchiseRequest.name());
    }
}
