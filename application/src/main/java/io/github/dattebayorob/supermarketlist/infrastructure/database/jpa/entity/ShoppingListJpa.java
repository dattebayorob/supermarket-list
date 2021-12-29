package io.github.dattebayorob.supermarketlist.infrastructure.database.jpa.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "shoppinglist")
@Getter
@Setter
@ToString(callSuper = true)
public class ShoppingListJpa extends JpaEntity{
    private boolean locked;
    @Column(name = "createdat", updatable = false)
    private LocalDateTime createdAt;
}
