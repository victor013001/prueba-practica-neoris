package com.neoris.evalart.prueba_practica_neoris.application.service.impl;

import com.neoris.evalart.prueba_practica_neoris.application.mapper.BranchMapper;
import com.neoris.evalart.prueba_practica_neoris.application.service.BranchService;
import com.neoris.evalart.prueba_practica_neoris.application.service.FranchiseService;
import com.neoris.evalart.prueba_practica_neoris.domain.model.Branch;
import com.neoris.evalart.prueba_practica_neoris.infrastructure.dto.BranchDto;
import com.neoris.evalart.prueba_practica_neoris.infrastructure.dto.BranchRequestDto;
import com.neoris.evalart.prueba_practica_neoris.infrastructure.exception.standard_exception.BranchAlreadyExist;
import com.neoris.evalart.prueba_practica_neoris.infrastructure.exception.standard_exception.BranchNotExist;
import com.neoris.evalart.prueba_practica_neoris.infrastructure.exception.standard_exception.NothingToChange;
import com.neoris.evalart.prueba_practica_neoris.infrastructure.repository.BranchRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class BranchServiceImpl implements BranchService {
    private final String LOG_PREFIX = "[Default_Branch_Service] >>> ";

    private final BranchRepository branchRepository;
    private final BranchMapper branchMapper;
    private final FranchiseService franchiseService;

    @Override
    public Mono<BranchDto> createBranch(BranchRequestDto branchRequestDto) {
        String branchName = branchRequestDto.name();
        return checkBranchUnique(branchName)
                .then(franchiseService.getFranchiseIdByUuid(branchRequestDto.franchiseUuid()))
                .flatMap(franchiseId -> {
                    Branch branch = branchMapper.toBranch(branchRequestDto, franchiseId);
                    log.info("{} Saving Branch with UUID {}", LOG_PREFIX, branch.getUuid());
                    return branchRepository.save(branch)
                            .map(branchMapper::toBranchDto);
                });
    }

    @Override
    public Mono<Long> getBranchIdByUuid(String uuid) {
        return checkBranchUuidExist(uuid)
                .then(branchRepository.getIdByUuid(uuid));
    }

    @Override
    public Mono<Void> updateBranchName(String branchUuid, String name) {
        return checkBranchUuidExist(branchUuid)
                .then(compareBranchNameWithCurrent(branchUuid, name))
                .then(checkBranchUnique(name))
                .then(branchRepository.updateName(branchUuid, name));
    }

    private Mono<Void> checkBranchUnique(String branchName) {
        log.info("{} Checking if Branch with name {} exists", LOG_PREFIX, branchName);
        return branchRepository.existsByName(branchName)
                .flatMap(exist -> exist ? Mono.error(BranchAlreadyExist::new) : Mono.empty());
    }

    private Mono<Void> checkBranchUuidExist(String branchUuid) {
        log.info("{} Checking if Branch Uuid {} exist", LOG_PREFIX, branchUuid);
        return branchRepository.existsByUuid(branchUuid)
                .flatMap(exist -> exist ? Mono.empty() : Mono.error(BranchNotExist::new));
    }

    private Mono<Void> compareBranchNameWithCurrent(String branchUuid, String name) {
        log.info("{} Comparing Branch name {} with current Branch {}", LOG_PREFIX, name, branchUuid);
        return branchRepository.isNameEquals(branchUuid, name)
                .flatMap(result -> result == 1 ? Mono.error(NothingToChange::new) : Mono.empty());
    }
}
