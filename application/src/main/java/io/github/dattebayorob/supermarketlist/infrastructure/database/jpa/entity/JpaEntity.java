package io.github.dattebayorob.supermarketlist.infrastructure.database.jpa.entity;

import io.github.dattebayorob.supermarketlist.domain.IdAware;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

@ToString
@EqualsAndHashCode(of = "id")
@MappedSuperclass
@GenericGenerator( name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
public abstract  class JpaEntity implements IdAware {
    @Id
    @GeneratedValue(generator = "uuid")
    private UUID id;
    @Override
    public UUID getId() {
        return id;
    }
    @Override
    public void setId(UUID id) {
        this.id = id;
    }
}
