package io.github.dattebayorob.supermarketlist.domain.filter;

import io.github.dattebayorob.supermarketlist.common.StringUtil;
import io.github.dattebayorob.supermarketlist.domain.util.PageFilter;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Getter
@EqualsAndHashCode
public class ProductFilters extends PageFilter {
    private String name;
    private UUID categoryId;
    private UUID shoppingListIdToIgnore;
    public ProductFilters(String name, String categoryId) {
        this(name, categoryId, null);
    }
    public ProductFilters(String name, String categoryId, String shoppingListIdToIgnore) {
        this(name, categoryId, shoppingListIdToIgnore, null, null, null);
    }
    public ProductFilters(String name, String categoryId, String shoppingListIdToIgnore, Integer page, Integer size, List<String> sort) {
        setName(name);
        setCategoryId(categoryId);
        setShoppingListIdToIgnore(shoppingListIdToIgnore);
        setPage(page);
        setSize(size);
        setSort(Optional.ofNullable(sort).orElseGet(() -> List.of("name asc")));
    }
    public void setName(String name) {
        this.name = name == null ? "" : name;
    }
    public void setCategoryId(String categoryId) {
        if (StringUtil.isNotEmpty(categoryId)) {
            this.categoryId = UUID.fromString(categoryId);
        }
    }
    public void setShoppingListIdToIgnore(String shoppingListIdToIgnore) {
        if (StringUtil.isNotEmpty(shoppingListIdToIgnore)) {
            this.shoppingListIdToIgnore = UUID.fromString(shoppingListIdToIgnore);
        }
    }
}
