package com.sticngo.rozliczajka.domain.user;

import org.springframework.stereotype.Repository;

/**
 * Created by Gladus on 26.06.2018.
 */
@Repository
public interface UserRepository extends
        com.sticngo.rozliczajka.infrastructures.persistence.Repository<User> {

    User findByLogin(String login);
}