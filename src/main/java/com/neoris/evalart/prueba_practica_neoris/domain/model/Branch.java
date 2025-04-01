package com.neoris.evalart.prueba_practica_neoris.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "branch")
public class Branch {
    @Id
    private Long id;
    private String uuid;
    private String name;
    @Transient
    private List<Product> products;
    @Column("franchise_id")
    private Long franchiseId;

    public List<Product> getProducts() {
        return Objects.isNull(products) ? Collections.emptyList() : products;
    }
}
