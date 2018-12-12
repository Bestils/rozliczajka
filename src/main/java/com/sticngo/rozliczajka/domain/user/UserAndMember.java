package com.sticngo.rozliczajka.domain.user;

import com.sticngo.rozliczajka.infrastructure.persistence.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAndMember  extends BaseEntity {

    private Long userId;
    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private String email;

}
