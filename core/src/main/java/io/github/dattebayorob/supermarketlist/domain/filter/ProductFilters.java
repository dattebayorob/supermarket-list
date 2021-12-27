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
    public ProductFilters(String name, String categoryId) {
        setName(name);
        setCategoryId(categoryId);
    }
    public void setName(String name) {
        this.name = name == null ? "" : name;
    }
    public void setCategoryId(String categoryId) {
        if (StringUtil.isNotEmpty(categoryId)) {
            this.categoryId = UUID.fromString(categoryId);
        }
    }
}
