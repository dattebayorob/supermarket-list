package io.github.dattebayorob.supermarketlist.domain;

import lombok.Data;

import java.util.UUID;

@Data
public class User implements IdAware{
    private UUID id;
    private String name;
    private String profilePictury;
    private String profileName;
}
