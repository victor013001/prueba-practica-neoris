package com.neoris.evalart.prueba_practica_neoris.infrastructure.repository;

import com.neoris.evalart.prueba_practica_neoris.domain.model.Branch;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface BranchRepository extends ReactiveCrudRepository<Branch, Long> {
    Mono<Boolean> existsByName(String branchName);

    Mono<Boolean> existsByUuid(String branchUuid);

    Mono<Long> getIdByUuid(String branchUuid);

    @Query("""
            SELECT EXISTS 
                (SELECT 1 
                 FROM product 
                 WHERE uuid = :uuid AND name = :name)                                  
            """)
    Mono<Integer> isNameEquals(String uuid, String name);

    @Modifying
    @Query("""
            UPDATE product 
            SET name = :name 
            WHERE uuid = :uuid
            """)
    Mono<Void> updateName(String uuid, String name);
}
