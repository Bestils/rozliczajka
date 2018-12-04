package com.sticngo.rozliczajka.infrastructure.security;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Service;
import com.sticngo.rozliczajka.domain.user.User;
import com.sticngo.rozliczajka.domain.user.UserRepository;

@Service
@RequiredArgsConstructor
public class SimpleSocialUserDetailsService implements SocialUserDetailsService {

  private final UserRepository userRepository;

  @Override
  public MySocialUserDetails loadUserByUserId(String userId)
      throws UsernameNotFoundException, DataAccessException {
    User user = userRepository.findById(Long.valueOf(userId)).get();
    return new MySocialUserDetails(user);
  }
}
