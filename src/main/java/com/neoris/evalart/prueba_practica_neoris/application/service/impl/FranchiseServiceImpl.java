package com.neoris.evalart.prueba_practica_neoris.application.service.impl;

import com.neoris.evalart.prueba_practica_neoris.application.mapper.FranchiseMapper;
import com.neoris.evalart.prueba_practica_neoris.application.service.FranchiseService;
import com.neoris.evalart.prueba_practica_neoris.domain.model.Franchise;
import com.neoris.evalart.prueba_practica_neoris.infrastructure.dto.FranchiseDto;
import com.neoris.evalart.prueba_practica_neoris.infrastructure.exception.standard_exception.FranchiseAlreadyExist;
import com.neoris.evalart.prueba_practica_neoris.infrastructure.exception.standard_exception.FranchiseNotExist;
import com.neoris.evalart.prueba_practica_neoris.infrastructure.repository.FranchiseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class FranchiseServiceImpl implements FranchiseService {
    private final String LOG_PREFIX = "[Default_Franchise_Service] >>> ";

    private final FranchiseRepository franchiseRepository;
    private final FranchiseMapper franchiseMapper;

    @Override
    public Mono<FranchiseDto> createFranchise(FranchiseDto franchiseDto) {
        String franchiseName = franchiseDto.name();
        return checkFranchiseNameUnique(franchiseName)
                .then(Mono.defer(() -> {
                    Franchise franchise = franchiseMapper.toFranchise(franchiseDto);
                    log.info("{} Saving Franchise with UUID {}", LOG_PREFIX, franchise.getUuid());
                    return franchiseRepository.save(franchise)
                            .map(franchiseMapper::toFranchiseDto);
                }));
    }

    @Override
    public Mono<Long> getFranchiseIdByUuid(String uuid) {
        return checkFranchiseUuidExist(uuid)
                .then(franchiseRepository.getIdByUuid(uuid));
    }

    private Mono<Void> checkFranchiseUuidExist(String franchiseUuid) {
        log.info("{} Checking if Franchise Uuid {} exist", LOG_PREFIX, franchiseUuid);
        return franchiseRepository.existsByUuid(franchiseUuid)
                .flatMap(exist -> exist ? Mono.empty() : Mono.error(FranchiseNotExist::new));
    }

    private Mono<Void> checkFranchiseNameUnique(String franchiseName) {
        log.info("{} Checking if Franchise with name {} is unique", LOG_PREFIX, franchiseName);
        return franchiseRepository.existsByName(franchiseName)
                .flatMap(exist -> exist ? Mono.error(FranchiseAlreadyExist::new) : Mono.empty());
    }
}
