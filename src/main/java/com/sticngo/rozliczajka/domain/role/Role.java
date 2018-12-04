package com.sticngo.rozliczajka.domain.role;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import com.sticngo.rozliczajka.domain.user.User;
import com.sticngo.rozliczajka.infrastructure.persistence.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(exclude = "users")
public class Role extends BaseEntity implements GrantedAuthority {

  @NotNull
  private String name;

  @ManyToMany(mappedBy = "roles")
  private List<User> users;

  @Override
  public String getAuthority() {
    return name;
  }
}
