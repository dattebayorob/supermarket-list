package io.github.dattebayorob.supermarketlist.domain;

import lombok.Data;

import java.util.UUID;

@Data
public class Product implements IdAware{
    private UUID id;
    private String name;
    private ProductCategory category;
}
