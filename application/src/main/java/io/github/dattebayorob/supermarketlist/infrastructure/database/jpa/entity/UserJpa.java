package io.github.dattebayorob.supermarketlist.infrastructure.database.jpa.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "userdetails")
@Getter
@Setter
public class UserJpa extends JpaEntity{
    @Column(name = "email", updatable = false)
    private String email;
    private String password;
    private String name;
    private boolean enabled;

    public static UserJpa robot() {
        final var user = new UserJpa();
        user.setId(UUID.fromString("378d0e65-d426-4652-9bd8-d84abc1e0f70"));
        return user;
    }
}
