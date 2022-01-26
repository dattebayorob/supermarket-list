package io.github.dattebayorob.supermarketlist.domain.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class PageFilter {
    private int page = 0;
    private int size = 10;
    private String sort;
    public void setPage(Integer page) {
        if ( page != null ) this.page = page;
    }
    public void setSize(Integer size) {
        if ( size != null ) this.size = size;
    }
}
