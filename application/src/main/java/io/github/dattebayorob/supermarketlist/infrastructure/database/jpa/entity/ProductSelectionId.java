package io.github.dattebayorob.supermarketlist.infrastructure.database.jpa.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ProductSelectionId implements Serializable {
    @Column(name = "product_id", nullable = false)
    private UUID productId;
    @Column(name = "shoppinglist_id", nullable = false)
    private UUID shoppingListId;
}
