package com.sticngo.rozliczajka.infrastructures.security;

import com.sticngo.rozliczajka.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.sticngo.rozliczajka.domain.user.User;
import com.sticngo.rozliczajka.domain.user.UserRepository;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByLogin(username);
    if(user == null) {
      throw new UsernameNotFoundException(username);
    }
    return new com.sticngo.rozliczajka.infrastructures.security.MyUserPrincipal(user);
  }
}
