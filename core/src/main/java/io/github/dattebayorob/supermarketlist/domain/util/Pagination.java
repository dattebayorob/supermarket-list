package io.github.dattebayorob.supermarketlist.domain.util;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@EqualsAndHashCode
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
    public void forEach(Consumer<T> consumer) {
        this.content.forEach(consumer);
    }
    public static final <U>Pagination<U> empty() {
        return new Pagination<U>(Collections.emptyList(), 0, 10, 0);
    }
    public static final <U>Pagination<U> of(List<U> content) {
        return of(content, content.size());
    }
    public static final <U>Pagination<U> of(List<U> content, long total) {
        return new Pagination<>(content, 0, 10, total);
    }
}
