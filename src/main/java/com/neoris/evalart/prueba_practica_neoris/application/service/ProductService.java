package com.neoris.evalart.prueba_practica_neoris.application.service;

import com.neoris.evalart.prueba_practica_neoris.infrastructure.dto.ProductDto;
import com.neoris.evalart.prueba_practica_neoris.infrastructure.dto.ProductRequestDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import reactor.core.publisher.Mono;

public interface ProductService {
    /**
     * Creates a new product in the database
     *
     * @param productRequestDto The validated productRequestDto
     * @return Publisher that emits the saved ProductDto with UUID
     */
    Mono<ProductDto> createProduct(ProductRequestDto productRequestDto);

    /**
     *  Updates the Product
     *
     * @param productDto The Product with the uuid, the new stock value and new name
     */
    Mono<Void> updateProduct(ProductDto productDto);

    /**
     * Delete Product from Branch
     * @param productUuid Product Uuid to delete
     */
    Mono<Void> deleteProduct(@Valid @NotBlank(message = "productUUid is mandatory") String productUuid);
}
