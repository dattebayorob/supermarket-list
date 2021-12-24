package io.github.dattebayorob.supermarketlist.port.out;

import io.github.dattebayorob.supermarketlist.domain.ProductSelection;

import java.util.List;

public interface FindCurrentProductListService {
    List<ProductSelection> findCurrentProductList();
}
