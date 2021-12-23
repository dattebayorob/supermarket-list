package io.github.dattebayorob.supermarketlist.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProductList {
    private boolean empty;
    private LocalDateTime createdAt;
}
