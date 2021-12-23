package io.github.dattebayorob.supermarketlist.exception;

import java.util.UUID;

public class ShoppingListNotFoundException extends ResourceNotFoundException{
    public ShoppingListNotFoundException(UUID id) {
        super("No shopping list found for id "+id);
    }
}
