package io.github.dattebayorob.supermarketlist.infrastructure.mapper;

import io.github.dattebayorob.supermarketlist.common.CollectionUtil;
import io.github.dattebayorob.supermarketlist.common.StringUtil;
import io.github.dattebayorob.supermarketlist.domain.util.PageFilter;
import io.github.dattebayorob.supermarketlist.domain.util.Pagination;
import io.github.dattebayorob.supermarketlist.presentation.rest.representation.PaginationResponse;
import org.springframework.data.domain.*;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

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
        if(pageFilter.getSize()==0) {
            return new UnpagedAndSortable().withSort(getSort(pageFilter.getSort()));
        }
        return PageRequest.of(pageFilter.getPage(), pageFilter.getSize(), getSort(pageFilter.getSort()));
    }

    private Sort getSort(List<String> sorts) {
        if(CollectionUtil.isEmpty(sorts)) return Sort.unsorted();
        var orders = mapOrders(sorts);
        return CollectionUtil.isNotEmpty(orders) ? Sort.by(orders) : Sort.unsorted();
    }

    private List<Sort.Order> mapOrders(List<String> sorts) {
        return sorts.stream().map(toOrder()).filter(Objects::nonNull).collect(Collectors.toList());
    }

    private Function<String, Sort.Order> toOrder() {
        return str -> {
            if (StringUtil.isEmpty(str) || str.split(" ").length == 0) return null;
            String[] sorts = str.split(" ");
            String property = sorts[0].trim();
            String direction = "asc";
            if ( sorts.length > 1 && StringUtil.isNotEmpty(sorts[1].trim())) {
                direction = sorts[1].trim();
            }
            return new Sort.Order(Sort.Direction.fromString(direction.toUpperCase()), property);
        };
    }

    public static class UnpagedAndSortable implements Pageable {
        private Sort sort = Sort.unsorted();
        @Override
        public boolean isPaged() {
            return false;
        }
        @Override
        public Pageable previousOrFirst() {
            return this;
        }
        @Override
        public Pageable next() {
            return this;
        }
        @Override
        public boolean hasPrevious() {
            return false;
        }
        @Override
        public Sort getSort() {
            return this.sort;
        }
        @Override
        public int getPageSize() {
            throw new UnsupportedOperationException();
        }
        @Override
        public int getPageNumber() {
            throw new UnsupportedOperationException();
        }
        @Override
        public long getOffset() {
            throw new UnsupportedOperationException();
        }
        @Override
        public Pageable first() {
            return this;
        }
        @Override
        public Pageable withPage(int pageNumber) {
            if (pageNumber == 0) {
                return this;
            }
            throw new UnsupportedOperationException();
        }
        public Pageable withSort(Sort sort) {
            this.sort = sort;
            return this;
        }
    }
}
