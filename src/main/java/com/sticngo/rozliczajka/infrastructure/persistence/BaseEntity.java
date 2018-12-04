package com.sticngo.rozliczajka.infrastructure.persistence;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;


@Data
@MappedSuperclass
public class BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Version
  private Long version;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o.getClass().isInstance(this.getClass())) {
      BaseEntity entity = (BaseEntity) o;
      return getId() != null && Objects.equals(getId(), entity.getId());
    } else {
      return false;
    }
  }

  @Override
  public int hashCode() {
    return 31;
  }
}
