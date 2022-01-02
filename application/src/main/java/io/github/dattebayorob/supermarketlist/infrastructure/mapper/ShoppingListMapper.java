package io.github.dattebayorob.supermarketlist.infrastructure.mapper;

import io.github.dattebayorob.supermarketlist.domain.ShoppingList;
import io.github.dattebayorob.supermarketlist.infrastructure.database.jpa.entity.ShoppingListJpa;
import io.github.dattebayorob.supermarketlist.infrastructure.mapper.abstraction.DomainMapper;
import io.github.dattebayorob.supermarketlist.infrastructure.mapper.abstraction.ResponseMapper;
import io.github.dattebayorob.supermarketlist.presentation.rest.representation.ShoppingListResponse;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;
import java.util.UUID;

@Component
public class ShoppingListMapper implements
        DomainMapper<ShoppingList, ShoppingListJpa>,
        ResponseMapper<ShoppingList, ShoppingListResponse> {
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

    @Override
    public ShoppingListResponse toResponse(ShoppingList domain) {
        return new ShoppingListResponse()
                .id(domain.getId().toString())
                .empty(domain.isEmpty())
                .locked(domain.isLocked())
                .createdAt(domain.getCreatedAt().atOffset(ZoneOffset.UTC));
    }
}
