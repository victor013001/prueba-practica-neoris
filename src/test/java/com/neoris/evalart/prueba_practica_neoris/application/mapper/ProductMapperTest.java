package com.neoris.evalart.prueba_practica_neoris.application.mapper;

import com.neoris.evalart.prueba_practica_neoris.domain.model.Product;
import com.neoris.evalart.prueba_practica_neoris.infrastructure.dto.ProductDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.neoris.evalart.prueba_practica_neoris.util.ProductDataUtil.getProduct;
import static com.neoris.evalart.prueba_practica_neoris.util.ProductDtoDataUtil.getProductDto;
import static com.neoris.evalart.prueba_practica_neoris.util.ProductDtoDataUtil.getProductDtoWithUuidNull;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductMapperTest {
    private ProductMapper ProductMapper;

    @BeforeEach
    void setUp() {
        ProductMapper = new ProductMapperImpl();
    }

    @Test
    void testToProductDto() {
        Product Product = getProduct();
        ProductDto ProductDto = ProductMapper.toProductDto(Product);
        assertProductDto(ProductDto, Product);
    }

    @Test
    void testToProduct() {
        ProductDto ProductDto = getProductDto();
        Product Product = ProductMapper.toProduct(ProductDto);
        assertProduct(Product, ProductDto);
    }

    @Test
    void testToProductWhenUuidNull() {
        ProductDto ProductDto = getProductDtoWithUuidNull();
        Product Product = ProductMapper.toProduct(ProductDto);
        assertNull(ProductDto.uuid());
        assertNotNull(Product.getUuid());
    }

    private void assertProduct(Product expected, ProductDto actual) {
        assertNotNull(actual);
        assertEquals(expected.getUuid(), actual.uuid());
        assertEquals(expected.getName(), actual.name());
        assertEquals(expected.getStock(), actual.stock());
    }

    private void assertProductDto(ProductDto expected, Product actual) {
        assertNotNull(actual);
        assertEquals(expected.uuid(), actual.getUuid());
        assertEquals(expected.name(), actual.getName());
        assertEquals(expected.stock(), actual.getStock());
    }
}