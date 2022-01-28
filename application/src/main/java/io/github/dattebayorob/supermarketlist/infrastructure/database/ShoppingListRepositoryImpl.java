package io.github.dattebayorob.supermarketlist.infrastructure.database;

import io.github.dattebayorob.supermarketlist.domain.ShoppingList;
import io.github.dattebayorob.supermarketlist.domain.filter.ShoppingListFilters;
import io.github.dattebayorob.supermarketlist.domain.util.Pagination;
import io.github.dattebayorob.supermarketlist.infrastructure.database.jpa.repository.ShoppingListJpaRepository;
import io.github.dattebayorob.supermarketlist.infrastructure.database.jpa.specification.FindShoppingListsByFiltersSpecification;
import io.github.dattebayorob.supermarketlist.infrastructure.mapper.PaginationMapper;
import io.github.dattebayorob.supermarketlist.infrastructure.mapper.ShoppingListMapper;
import io.github.dattebayorob.supermarketlist.port.in.ShoppingListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class ShoppingListRepositoryImpl implements ShoppingListRepository {
    private final ShoppingListJpaRepository shoppingListJpaRepository;
    private final ShoppingListMapper shoppingListMapper;
    private final FindShoppingListsByFiltersSpecification specification;
    private final PaginationMapper paginationMapper;
    @Override
    public Optional<ShoppingList> findById(UUID id) {
        return shoppingListJpaRepository.findById(id).map(shoppingListMapper::toDomain);
    }

    @Override
    public Pagination<ShoppingList> findAll(ShoppingListFilters shoppingListFilters) {
        var spec = specification.findShoppingListsByFilters(shoppingListFilters);
        var pageable = paginationMapper.toPageable(shoppingListFilters);
        var page = shoppingListJpaRepository.findAll(spec,pageable).map(shoppingListMapper::toDomain);
        return paginationMapper.toPagination(page);
    }

    @Override
    public ShoppingList save(ShoppingList shoppingList) {
        return shoppingListMapper.toDomain(shoppingListJpaRepository.save(
                shoppingListMapper.toEntity(shoppingList)
        ));
    }
}
