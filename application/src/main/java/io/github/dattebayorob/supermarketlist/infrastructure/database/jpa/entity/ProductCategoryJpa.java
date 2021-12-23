package io.github.dattebayorob.supermarketlist.infrastructure.database.jpa.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "product_category")
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ProductCategoryJpa extends JpaEntity{
    private String name;
}
