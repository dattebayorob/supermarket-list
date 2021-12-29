package io.github.dattebayorob.supermarketlist.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
public class ShoppingList implements IdAware{
    private UUID id;
    private boolean empty;
    private boolean locked;
    private LocalDateTime createdAt;
    public ShoppingList(UUID id) {
        this.id = id;
    }
}
