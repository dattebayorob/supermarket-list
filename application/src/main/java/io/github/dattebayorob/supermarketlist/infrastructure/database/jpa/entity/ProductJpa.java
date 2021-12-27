package io.github.dattebayorob.supermarketlist.infrastructure.database.jpa.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "product")
@Getter
@Setter
@ToString(callSuper = true)
public class ProductJpa extends JpaEntity {
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private ProductCategoryJpa category;
}
