package io.github.dattebayorob.supermarketlist.infrastructure.database.jpa.specification;

import io.github.dattebayorob.supermarketlist.common.StringUtil;
import io.github.dattebayorob.supermarketlist.domain.filter.ProductFilters;
import io.github.dattebayorob.supermarketlist.infrastructure.database.jpa.entity.ProductJpa;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Component
public class FindProductsByFiltersSpecification {
    public Specification<ProductJpa> findProductsByFilters(ProductFilters filters) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtil.isNotEmpty(filters.getName())) {
                predicates.add(
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%"+ filters.getName()+"%")
                );
            }
            if (filters.getCategoryId() != null) {
                predicates.add(
                        criteriaBuilder.equal(root.get("category").get("id"), filters.getCategoryId())
                );
            }
            return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
        };
    }
}
