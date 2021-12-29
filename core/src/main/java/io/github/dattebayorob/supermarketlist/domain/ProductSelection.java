package io.github.dattebayorob.supermarketlist.domain;

import lombok.Data;

@Data
public class ProductSelection {
    private Product product;
    private Integer quantity;
    private ShoppingList shoppingList;
    private User user;
}
