package com.neoris.evalart.prueba_practica_neoris.infrastructure.repository;

import com.neoris.evalart.prueba_practica_neoris.domain.model.Product;
import com.neoris.evalart.prueba_practica_neoris.infrastructure.dto.ProductDto;
import com.neoris.evalart.prueba_practica_neoris.infrastructure.dto.TopProductDto;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
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

    @Query("""
            SELECT CASE WHEN COUNT(*) > 0 
                THEN TRUE ELSE FALSE END 
            FROM product 
            WHERE name = :productName AND uuid <> :productUuid
            """)
    Mono<Integer> newNameUnique(String productName, String productUuid);

    @Query("""
                SELECT b.name AS branch_name, 
                       p.name AS product_name, 
                       MAX(p.stock) AS stock 
                FROM franchise f 
                    JOIN branch b ON f.id = b.franchise_id 
                    JOIN product p ON b.id = p.branch_id 
                WHERE f.uuid = :franchiseUuid 
                GROUP BY b.id ORDER BY stock DESC
            """)
    Flux<TopProductDto> findTopStockProductsByFranchise(String franchiseUuid);
}
