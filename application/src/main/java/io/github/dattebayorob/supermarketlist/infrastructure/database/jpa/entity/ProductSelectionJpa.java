package io.github.dattebayorob.supermarketlist.infrastructure.database.jpa.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "product_selection")
public class ProductSelectionJpa {
    @EmbeddedId
    private ProductSelectionId id;
    @Column(name = "user_id")
    private UUID userId;
    private int quantity;
    private boolean checked;
    public ProductSelectionJpa(UUID productId, UUID shoppingListId) {
        this.id = new ProductSelectionId(productId, shoppingListId);
    }
    public ProductSelectionJpa(UUID productId, UUID shoppingListId, int quantity) {
        this(productId, shoppingListId);
        this.quantity = quantity;
    }
    public  ProductSelectionJpa(UUID productId, UUID shoppingListId, int quantity, UUID userId) {
        this(productId, shoppingListId, quantity);
        this.userId = userId;
    }
}
