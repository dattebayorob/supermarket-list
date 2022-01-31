package io.github.dattebayorob.supermarketlist.infrastructure.mapper;

import io.github.dattebayorob.supermarketlist.domain.util.PageFilter;
import io.github.dattebayorob.supermarketlist.domain.util.Pagination;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PaginationMapperTest {
    PaginationMapper paginationMapper = new PaginationMapper();
    @Test
    void shouldMapSpringPageToPagination() {
        var content = Arrays.asList("A", "B", "C");
        var pageable = PageRequest.of(1, 3);
        long total = 10;
        var page = new PageImpl<>(content, pageable, total);
        var pagination = paginationMapper.toPagination(page);
        assertEquals(page.getNumber(), pagination.getPage());
        assertEquals(page.getSize(), pagination.getSize());
        assertEquals(page.getTotalElements(), pagination.getTotal());
    }

    @Test
    void shouldMapPaginationToSpringPage() {
        var content = Arrays.asList("A", "B", "C");
        int pageNumber = 1;
        int size = 3;
        long total = 10;
        var pagination = new Pagination<>(content, pageNumber, size, total);
        var page = paginationMapper.toPage(pagination);
        assertEquals(pageNumber, page.getNumber());
        assertEquals(size, page.getSize());
        assertEquals(total, page.getTotalElements());
    }

    @Test
    void shouldMapSort() {
        PageFilter filter = new PageFilter() {};
        filter.setPage(0);
        filter.setSize(10);
        filter.setSort(List.of( "id desc", "name  asc", "createdAt desc" ));
        Pageable pageable = paginationMapper.toPageable(filter);
        Assertions.assertTrue(pageable.getSort().getOrderFor("id").getDirection().isDescending());
        Assertions.assertTrue(pageable.getSort().getOrderFor("name").getDirection().isAscending());
        Assertions.assertTrue(pageable.getSort().getOrderFor("createdAt").getDirection().isDescending());
    }

    @Test
    void shouldMapUnpagedButSortedPageable() {
        PageFilter filter = new PageFilter() {};
        filter.setPage(0);
        filter.setSize(0);
        filter.setSort(List.of("id desc", "name asc"));
        Pageable pageable = paginationMapper.toPageable(filter);
        Assertions.assertTrue(pageable.isUnpaged());
        Assertions.assertTrue(pageable.getSort().getOrderFor("id").getDirection().isDescending());
        Assertions.assertTrue(pageable.getSort().getOrderFor("name").getDirection().isAscending());    }

}