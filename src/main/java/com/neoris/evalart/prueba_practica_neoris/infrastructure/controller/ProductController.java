package com.neoris.evalart.prueba_practica_neoris.infrastructure.controller;

import com.neoris.evalart.prueba_practica_neoris.application.service.ProductService;
import com.neoris.evalart.prueba_practica_neoris.infrastructure.dto.ProductDto;
import com.neoris.evalart.prueba_practica_neoris.infrastructure.dto.ProductRequestDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/product")
public class ProductController {
    private final String LOG_PREFIX = "[Product_Controller_V1] >>> ";

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ProductDto> createProduct(@Valid @RequestBody final ProductRequestDto productRequest) {
        log.info("{} Creating Product with Name {}", LOG_PREFIX, productRequest.name());
        return productService.createProduct(productRequest);
    }

    @PatchMapping
    public Mono<Void> updateProductStock(@Valid @RequestBody final ProductDto productRequest) {
        log.info("{} Updating Stock to Product {}", LOG_PREFIX, productRequest.uuid());
        return productService.updateProduct(productRequest);
    }

}
