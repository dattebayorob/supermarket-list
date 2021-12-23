package io.github.dattebayorob.supermarketlist.exception;

import java.util.UUID;

public class ProductNotFoundException extends ResourceNotFoundException {
    public ProductNotFoundException(UUID id) {
        super("No product found for id "+id);
    }
}
