package io.github.dattebayorob.supermarketlist.domain;

import java.util.UUID;

public interface IdAware {
    UUID getId();
    void setId(UUID id);
}
