package com.sticngo.rozliczajka.domain.category;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import com.sticngo.rozliczajka.domain.task.Task;
import com.sticngo.rozliczajka.domain.user.User;
import com.sticngo.rozliczajka.infrastructure.persistence.BaseEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(exclude = {"tasks", "user"})
public class Category extends BaseEntity {
    private String name;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
    @OrderColumn(name = "position")
    private List<Task> tasks;

    @ManyToOne
    @JoinColumn(name ="user_id")
    private User user;
}