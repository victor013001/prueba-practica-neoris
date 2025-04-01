package com.neoris.evalart.prueba_practica_neoris.infrastructure.repository;

import com.neoris.evalart.prueba_practica_neoris.domain.model.Product;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface ProductRepository extends ReactiveCrudRepository<Product, Long> {
    Mono<Boolean> existsByName(String productName);

    Mono<Boolean> existsByUuid(String productUuid);

    @Modifying
    @Query("""
            UPDATE product
            SET stock = :newStock,
                name = :newName
            WHERE uuid = :uuid
            """)
    Mono<Void> updateProductByUuid(String uuid, int newStock, String newName);

    Mono<Void> deleteByUuid(String productUuid);
}
