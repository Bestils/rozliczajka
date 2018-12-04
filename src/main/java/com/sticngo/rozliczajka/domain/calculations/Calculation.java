package com.sticngo.rozliczajka.domain.calculations;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.sticngo.rozliczajka.domain.members.Member;
import com.sticngo.rozliczajka.domain.role.Role;
import com.sticngo.rozliczajka.domain.task.Task;
import com.sticngo.rozliczajka.domain.user.User;
import com.sticngo.rozliczajka.infrastructure.persistence.BaseEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(exclude = {"category", "user"})
public class Calculation extends BaseEntity {

  @NotNull
  @Size(min = 3)
  private String name;

  @NotNull
  @Size(min = 5)
  private String description;

  private double value;

  private Boolean finished = false;

  @ManyToOne
  @JoinColumn(name = "category_id")
  private Member member;

  @ManyToOne
  @JoinColumn(name ="user_id")
  private User user;

  @JsonIgnore
  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
          name = "Users_Roles",
          joinColumns = {@JoinColumn(name = "user_id")},
          inverseJoinColumns = {@JoinColumn(name = "role_id")}
  )
  private Set<Role> roles;

  private int position;

//  public void updateMember(Category category, int position) {
//    if(getCategory() != null)
//      this.category.getTasks().remove(this);
//    this.category = category;
//    this.category.getTasks().add(position, this);
//  }

//  public void updateUser(User user) {
//    if(getUser() != null)
//      this.user.getTask().remove(this);
//    this.user = user;
//    this.user.addCalculation(this);
//  }
}
