package io.github.dattebayorob.supermarketlist.infrastructure.mapper;

import io.github.dattebayorob.supermarketlist.domain.util.PageFilter;
import io.github.dattebayorob.supermarketlist.domain.util.Pagination;
import io.github.dattebayorob.supermarketlist.presentation.rest.representation.PaginationResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class PaginationMapper {
    public <T>Pagination<T> toPagination(Page<T> page) {
        return new Pagination<>(
                page.getContent(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements()
        );
    }
    public <T>PaginationResponse toResponse(Pagination<T> pagination) {
        return new PaginationResponse()
                .content((List<Object>) pagination.getContent())
                .page(pagination.getPage())
                .size(pagination.getSize())
                .total((int)pagination.getTotal());
    }
    public <T>Page<T> toPage(Pagination<T> pagination) {
        final var pageable = PageRequest.of(pagination.getPage(), pagination.getSize());
        return new PageImpl<>(pagination.getContent(), pageable, pagination.getTotal());
    }
    public Pageable toPageable(PageFilter pageFilter) {
        return PageRequest.of(pageFilter.getPage(), pageFilter.getSize());
    }
}
