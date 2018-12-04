package com.sticngo.rozliczajka.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.sticngo.rozliczajka.domain.calculations.Calculation;
import com.sticngo.rozliczajka.domain.members.Member;
import lombok.*;
import com.sticngo.rozliczajka.domain.category.Category;
import com.sticngo.rozliczajka.domain.role.Role;
import com.sticngo.rozliczajka.domain.task.Task;
import com.sticngo.rozliczajka.infrastructure.persistence.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(exclude = {"calculation", "category", "roles","members"})
public class User extends BaseEntity {
  private String name;
  private String surrname;

  @Column(name = "login", nullable = false, unique = true)
  @NotNull(message = "Login  is required")
  @NotBlank(message = "Login is required")
  @Pattern(regexp = "[A-Za-z0-9\\d]{9,255}$", message = "Login has invalid characters")
  private String login;


  @NotNull(message = "Password is required")
  @NotBlank(message = "Password is required")
  @Pattern(regexp = "[a-zA-Z0-9]{12,}", message = "Password  has invalid characters")
  @JsonProperty(access = Access.WRITE_ONLY)
  private String password;

  @NotNull(message = "Email Address is required")
  @NotBlank(message = "Email Address is required")
  @Email(message = "Email address has invalid format")
  private String email;



  private Boolean enabled = true;

//  @JsonIgnore
//  @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
//  private List<Task> task;
//

  @JsonIgnore
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
  private List<Calculation> calculation;

  @JsonIgnore
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
  private List<Category> category;

  @JsonIgnore
  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
          name = "Users_Roles",
          joinColumns = {@JoinColumn(name = "user_id")},
          inverseJoinColumns = {@JoinColumn(name = "role_id")}
  )
  private Set<Role> roles;

  private Set<Member> members;

//  public void addTask(Task task) {
//    this.task.add(task);
//  }

  public void addCalculation(Calculation calculation) {
    this.calculation.add(calculation);
  }

}
