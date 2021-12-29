package io.github.dattebayorob.supermarketlist.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class Product implements IdAware{
    private UUID id;
    private String name;
    private ProductCategory category;
    public Product(UUID id) {
        this.id = id;
    }
}
