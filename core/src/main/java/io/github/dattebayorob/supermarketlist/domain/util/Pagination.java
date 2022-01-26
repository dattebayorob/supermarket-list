package io.github.dattebayorob.supermarketlist.domain.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class Pagination <T>{
    private List<T> content;
    private int page;
    private int size;
    private long total;
    public <U>Pagination<U> map(Function<T, U> mapper) {
        return new Pagination<U>(
                this.content.stream().map(mapper).collect(Collectors.toList()),
                this.page,
                this.size,
                this.total
        );
    }
    public static final <U>Pagination<U> empty() {
        return new Pagination<U>(Collections.emptyList(), 0, 10, 0);
    }
}
