package com.sticngo.rozliczajka.domain.members;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sticngo.rozliczajka.domain.calculations.Calculation;

import com.sticngo.rozliczajka.domain.user.User;
import com.sticngo.rozliczajka.infrastructure.persistence.BaseEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(exclude = {"calculation", "user"})
public class Member extends BaseEntity {



    private String firstName;
    private String lastName;

    @NotNull(message = "Email Address is required")
    @NotBlank(message = "Email Address is required")
    @Email(message = "Email address has invalid format")
    private String email;


    @ManyToMany(mappedBy = "members")
    private List<Calculation> calculations;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "calculation")
    @OrderColumn(name = "position")
    private List<Calculation> calculation;

    @OneToOne
    @JoinColumn(name ="user_id")
    private User user;


    private String shortName= "" +firstName.charAt(0)+lastName.charAt(0);;


}