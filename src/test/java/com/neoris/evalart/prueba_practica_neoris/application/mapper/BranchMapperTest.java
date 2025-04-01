package com.neoris.evalart.prueba_practica_neoris.application.mapper;

import com.neoris.evalart.prueba_practica_neoris.domain.model.Branch;
import com.neoris.evalart.prueba_practica_neoris.infrastructure.dto.BranchDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.neoris.evalart.prueba_practica_neoris.util.BranchDataUtil.getBranch;
import static com.neoris.evalart.prueba_practica_neoris.util.BranchDtoDataUtil.getBranchDto;
import static com.neoris.evalart.prueba_practica_neoris.util.BranchDtoDataUtil.getBranchDtoWithUuidNull;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BranchMapperTest {
    private BranchMapper BranchMapper;

    @BeforeEach
    void setUp() {
        BranchMapper = new BranchMapperImpl();
    }

    @Test
    void testToBranchDto() {
        Branch Branch = getBranch();
        BranchDto BranchDto = BranchMapper.toBranchDto(Branch);
        assertBranchDto(BranchDto, Branch);
    }

    @Test
    void testToBranch() {
        BranchDto BranchDto = getBranchDto();
        Branch Branch = BranchMapper.toBranch(BranchDto);
        assertBranch(Branch, BranchDto);
    }

    @Test
    void testToBranchWhenUuidNull() {
        BranchDto BranchDto = getBranchDtoWithUuidNull();
        Branch Branch = BranchMapper.toBranch(BranchDto);
        assertNull(BranchDto.uuid());
        assertNotNull(Branch.getUuid());
    }

    private void assertBranch(Branch expected, BranchDto actual) {
        assertNotNull(actual);
        assertEquals(expected.getUuid(), actual.uuid());
        assertEquals(expected.getName(), actual.name());
    }

    private void assertBranchDto(BranchDto expected, Branch actual) {
        assertNotNull(actual);
        assertEquals(expected.uuid(), actual.getUuid());
        assertEquals(expected.name(), actual.getName());
    }
}