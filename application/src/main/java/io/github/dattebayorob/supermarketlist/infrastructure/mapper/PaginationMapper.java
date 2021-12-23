package io.github.dattebayorob.supermarketlist.infrastructure.mapper;

import io.github.dattebayorob.supermarketlist.domain.util.Pagination;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

public class PaginationMapper {
    public <T>Pagination<T> toPagination(Page<T> page) {
        return new Pagination<>(
                page.getContent(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements()
        );
    }
    public <T>Page<T> toPage(Pagination<T> pagination) {
        final var pageable = PageRequest.of(pagination.getPage(), pagination.getSize());
        return new PageImpl<>(pagination.getContent(), pageable, pagination.getTotal());
    }
}
