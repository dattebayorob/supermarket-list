package io.github.dattebayorob.supermarketlist.domain.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class Pagination <T>{
    private List<T> content;
    private int page;
    private int size;
    private long total;
}
