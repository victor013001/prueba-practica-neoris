package com.neoris.evalart.prueba_practica_neoris.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "franchise")
public class Franchise {
    @Id
    private Long id;
    private UUID uuid;
    private String name;
    @Transient
    private List<Branch> branches;

    public List<Branch> getBranches() {
        return Objects.isNull(branches) ? Collections.emptyList() : branches;
    }
}
