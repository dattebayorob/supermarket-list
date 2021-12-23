package io.github.dattebayorob.supermarketlist.domain;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ShoppingList implements IdAware{
    private UUID id;
    private boolean empty;
    private boolean locked;
    private LocalDateTime createdAt;
}
