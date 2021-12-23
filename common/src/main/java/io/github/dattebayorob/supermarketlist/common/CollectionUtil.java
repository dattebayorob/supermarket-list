package io.github.dattebayorob.supermarketlist.common;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

public class CollectionUtil {
    private CollectionUtil(){}
    public static <T, U>List<U> map(List<T> collection, Function<T, U> mapper) {
        if ( collection == null || collection.isEmpty() ) return Collections.emptyList();
        return collection.stream().map(mapper).collect(toList());
    }
    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }
    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

}
