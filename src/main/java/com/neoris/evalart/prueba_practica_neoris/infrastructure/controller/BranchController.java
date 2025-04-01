package com.neoris.evalart.prueba_practica_neoris.infrastructure.controller;

import com.neoris.evalart.prueba_practica_neoris.application.service.BranchService;
import com.neoris.evalart.prueba_practica_neoris.infrastructure.dto.BranchDto;
import com.neoris.evalart.prueba_practica_neoris.infrastructure.dto.BranchRequestDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/branch")
public class BranchController {
    private final String LOG_PREFIX = "[Branch_Controller_V1] >>> ";

    private final BranchService branchService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<BranchDto> createBranch(@Valid @RequestBody final BranchRequestDto branchRequest) {
        log.info("{} Creating Branch with Name {}", LOG_PREFIX, branchRequest.name());
        return branchService.createBranch(branchRequest);
    }

}
