package io.github.dattebayorob.supermarketlist.domain;

import lombok.Data;

import java.util.UUID;

@Data
public class ProductSelection implements IdAware{
    private UUID id;
    private Product product;
    private Integer quantity;
    private ShoppingList shoppingList;
    private User user;
}
