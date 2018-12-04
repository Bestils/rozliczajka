package com.sticngo.rozliczajka.domain.members;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sticngo.rozliczajka.domain.calculations.Calculation;
import com.sticngo.rozliczajka.domain.task.Task;
import com.sticngo.rozliczajka.domain.user.User;
import com.sticngo.rozliczajka.infrastructure.persistence.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(exclude = {"calculation", "user"})
public class Member extends BaseEntity {
    private String name;

    @ManyToMany(mappedBy = "members")
    private List<Calculation> calculations;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "calculation")
    @OrderColumn(name = "position")
    private List<Calculation> calculation;

    @ManyToOne
    @JoinColumn(name ="user_id")
    private com.sticngo.rozliczajka.domain.user.User user;


    private String shortName;
}