package com.neoris.evalart.prueba_practica_neoris.infrastructure.repository;

import com.neoris.evalart.prueba_practica_neoris.domain.model.Franchise;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface FranchiseRepository extends ReactiveCrudRepository<Franchise, Long> {
    Mono<Boolean> existsByName(String franchiseName);

    Mono<Boolean> existsByUuid(String franchiseUuid);

    Mono<Long> getIdByUuid(String franchiseUuid);
}
