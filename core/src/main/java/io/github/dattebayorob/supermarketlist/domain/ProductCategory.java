package io.github.dattebayorob.supermarketlist.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductCategory implements IdAware{
    private UUID id;
    private String name;
}
