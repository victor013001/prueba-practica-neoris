package com.neoris.evalart.prueba_practica_neoris.application.mapper;

import com.neoris.evalart.prueba_practica_neoris.domain.model.Franchise;
import com.neoris.evalart.prueba_practica_neoris.infrastructure.dto.FranchiseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.neoris.evalart.prueba_practica_neoris.util.FranchiseDataUtil.getFranchise;
import static com.neoris.evalart.prueba_practica_neoris.util.FranchiseDtoDataUtil.getFranchiseDto;
import static com.neoris.evalart.prueba_practica_neoris.util.FranchiseDtoDataUtil.getFranchiseDtoWithUuidNull;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class FranchiseMapperTest {
    private FranchiseMapper franchiseMapper;

    @BeforeEach
    void setUp() {
        franchiseMapper = new FranchiseMapperImpl();
    }

    @Test
    void testToFranchiseDto() {
        Franchise franchise = getFranchise();
        FranchiseDto franchiseDto = franchiseMapper.toFranchiseDto(franchise);
        assertFranchiseDto(franchiseDto, franchise);
    }

    @Test
    void testToFranchise() {
        FranchiseDto franchiseDto = getFranchiseDto();
        Franchise franchise = franchiseMapper.toFranchise(franchiseDto);
        assertFranchise(franchise, franchiseDto);
    }

    @Test
    void testToFranchiseWhenUuidNull() {
        FranchiseDto franchiseDto = getFranchiseDtoWithUuidNull();
        Franchise franchise = franchiseMapper.toFranchise(franchiseDto);
        assertNull(franchiseDto.uuid());
        assertNotNull(franchise.getUuid());
    }

    private void assertFranchise(Franchise expected, FranchiseDto actual) {
        assertNotNull(actual);
        assertEquals(expected.getUuid(), actual.uuid());
        assertEquals(expected.getName(), actual.name());
    }

    private void assertFranchiseDto(FranchiseDto expected, Franchise actual) {
        assertNotNull(actual);
        assertEquals(expected.uuid(), actual.getUuid());
        assertEquals(expected.name(), actual.getName());
    }
}