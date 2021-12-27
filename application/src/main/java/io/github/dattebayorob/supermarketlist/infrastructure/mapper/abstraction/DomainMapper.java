package io.github.dattebayorob.supermarketlist.infrastructure.mapper.abstraction;

import io.github.dattebayorob.supermarketlist.common.CollectionUtil;

import java.util.List;

public interface DomainMapper<D,E> {
    E toEntity(D domain);
    default List<E> toEntity(List<D> domain) {
        return CollectionUtil.map(domain, this::toEntity);
    }
    D toDomain(E domain);
    default List<D> toDomain(List<E> entities) {
        return CollectionUtil.map(entities, this::toDomain);
    }
}
