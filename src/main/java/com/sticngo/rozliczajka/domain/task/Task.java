package com.sticngo.rozliczajka.domain.task;

import lombok.*;
import com.sticngo.rozliczajka.domain.category.Category;
import com.sticngo.rozliczajka.domain.user.User;
import com.sticngo.rozliczajka.infrastructure.persistence.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(exclude = {"category", "user"})
public class Task extends BaseEntity {

  @NotNull
  @Size(min = 3)
  private String name;

  @NotNull
  @Size(min = 5)
  private String description;

  private Integer priority;

  private Boolean finished = false;

  @ManyToOne
  @JoinColumn(name = "category_id")
  private Category category;

  @ManyToOne
  @JoinColumn(name ="user_id")
  private User user;

  private int position;

  public void updateCategory(Category category, int position) {
    if(getCategory() != null)
      this.category.getTasks().remove(this);
    this.category = category;
    this.category.getTasks().add(position, this);
  }

//  public void updateUser(User user) {
//    if(getUser() != null)
//      this.user.getTask().remove(this);
//    this.user = user;
//    this.user.addTask(this);
  }

