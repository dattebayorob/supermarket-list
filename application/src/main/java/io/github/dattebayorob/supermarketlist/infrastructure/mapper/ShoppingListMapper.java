package io.github.dattebayorob.supermarketlist.infrastructure.mapper;

import io.github.dattebayorob.supermarketlist.domain.ShoppingList;
import io.github.dattebayorob.supermarketlist.infrastructure.database.jpa.entity.ShoppingListJpa;
import io.github.dattebayorob.supermarketlist.infrastructure.mapper.abstraction.DomainMapper;
import org.springframework.stereotype.Component;

@Component
public class ShoppingListMapper implements DomainMapper<ShoppingList, ShoppingListJpa> {
    @Override
    public ShoppingListJpa toEntity(ShoppingList domain) {
        var entity = new ShoppingListJpa();
        entity.setId(domain.getId());
        entity.setLocked(domain.isLocked());
        entity.setCreatedAt(domain.getCreatedAt());
        return entity;
    }

    @Override
    public ShoppingList toDomain(ShoppingListJpa entity) {
        var domain = new ShoppingList();
        domain.setId(entity.getId());
        domain.setLocked(entity.isLocked());
        domain.setCreatedAt(entity.getCreatedAt());
        return domain;
    }
}
