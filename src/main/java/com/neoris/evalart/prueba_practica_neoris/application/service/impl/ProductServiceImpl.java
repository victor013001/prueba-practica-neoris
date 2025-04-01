package com.neoris.evalart.prueba_practica_neoris.application.service.impl;

import com.neoris.evalart.prueba_practica_neoris.application.mapper.ProductMapper;
import com.neoris.evalart.prueba_practica_neoris.application.service.BranchService;
import com.neoris.evalart.prueba_practica_neoris.application.service.ProductService;
import com.neoris.evalart.prueba_practica_neoris.domain.model.Product;
import com.neoris.evalart.prueba_practica_neoris.infrastructure.dto.ProductDto;
import com.neoris.evalart.prueba_practica_neoris.infrastructure.dto.ProductRequestDto;
import com.neoris.evalart.prueba_practica_neoris.infrastructure.exception.standard_exception.BranchNotExist;
import com.neoris.evalart.prueba_practica_neoris.infrastructure.exception.standard_exception.ProductAlreadyExist;
import com.neoris.evalart.prueba_practica_neoris.infrastructure.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final String LOG_PREFIX = "[Default_Product_Service] >>> ";

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final BranchService branchService;

    @Override
    public Mono<ProductDto> createProduct(ProductRequestDto productRequestDto) {
        String productName = productRequestDto.name();
        return checkProductUnique(productName)
                .then(branchService.getBranchIdByUuid(productRequestDto.branchUuid()))
                .flatMap(branchId -> {
                    Product product = productMapper.toProduct(productRequestDto, branchId);
                    log.info("{} Saving Product with UUID {}", LOG_PREFIX, product.getUuid());
                    return productRepository.save(product)
                            .map(productMapper::toProductDto);
                });
    }

    @Override
    public Mono<Void> updateProduct(ProductDto productDto) {
        return checkProductUuidExist(productDto.uuid())
                .then(productRepository.updateProductByUuid(productDto.uuid(), productDto.stock(), productDto.name()));
    }

    private Mono<Void> checkProductUnique(String productName) {
        log.info("{} Checking if Product with name {} exists", LOG_PREFIX, productName);
        return productRepository.existsByName(productName)
                .flatMap(exist -> exist ? Mono.error(ProductAlreadyExist::new) : Mono.empty());
    }

    private Mono<Void> checkProductUuidExist(String productUuid) {
        log.info("{} Checking if Branch Uuid {} exist", LOG_PREFIX, productUuid);
        return productRepository.existsByUuid(productUuid)
                .flatMap(exist -> exist ? Mono.empty() : Mono.error(BranchNotExist::new));
    }
}
