package io.github.dattebayorob.supermarketlist.domain.filter;

import io.github.dattebayorob.supermarketlist.common.StringUtil;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.UUID;

@Getter
@EqualsAndHashCode
public class ProductFilters {
    private String name;
    private UUID categoryId;
    private UUID shoppingListIdToIgnore;
    public ProductFilters(String name, String categoryId) {
        this(name, categoryId, null);
    }
    public ProductFilters(String name, String categoryId, String shoppingListIdToIgnore) {
        setName(name);
        setCategoryId(categoryId);
        setShoppingListIdToIgnore(shoppingListIdToIgnore);
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
