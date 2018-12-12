package com.sticngo.rozliczajka.infrastructure.security;

import com.sticngo.rozliczajka.domain.members.MemberRepository;
import com.sticngo.rozliczajka.domain.user.UserAndMember;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UserProfile;
import com.sticngo.rozliczajka.domain.role.RoleRepository;
import com.sticngo.rozliczajka.domain.user.User;
import com.sticngo.rozliczajka.domain.user.UserRepository;

import java.util.Collections;

@RequiredArgsConstructor
public class SecurityImplicitConnectionSignUp implements ConnectionSignUp {

  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final MemberRepository memberRepository;
  private final PasswordEncoder passwordEncoder;

  @Override
  public String execute(Connection<?> connection) {
    UserProfile profile = connection.fetchUserProfile();
    User newUser = new User(
            profile.getName().replaceAll("\\s+",""),
            passwordEncoder.encode("A" +  RandomStringUtils.randomAlphabetic(10) + "!"),

            true,

            Collections.singleton(roleRepository.getById(2L))



    );
    userRepository.save(newUser);
    return newUser.getId().toString();
  }
}