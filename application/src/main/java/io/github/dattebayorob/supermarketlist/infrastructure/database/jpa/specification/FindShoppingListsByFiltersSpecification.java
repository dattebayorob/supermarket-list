package io.github.dattebayorob.supermarketlist.infrastructure.database.jpa.specification;

import io.github.dattebayorob.supermarketlist.domain.filter.ShoppingListFilters;
import io.github.dattebayorob.supermarketlist.infrastructure.database.jpa.entity.ShoppingListJpa;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Component
public class FindShoppingListsByFiltersSpecification {
    public Specification<ShoppingListJpa> findShoppingListsByFilters(ShoppingListFilters filters) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if ( filters.getAfter() != null ) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("createdAt"), filters.getAfter()));
            }
            if ( filters.getBefore() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("createdAt"), filters.getBefore()));
            }
            return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
        };
    }
}
