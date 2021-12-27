package io.github.dattebayorob.supermarketlist.infrastructure.mapper.abstraction;

import io.github.dattebayorob.supermarketlist.common.CollectionUtil;

import java.util.List;

public interface ResponseMapper <D, R> {
    R toResponse(D domain);
    default List<R> toResponse(List<D> domain) {
        return CollectionUtil.map(domain, this::toResponse);
    }
}
