package com.sticngo.rozliczajka.infrastructure.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.social.security.SocialUserDetails;
import com.sticngo.rozliczajka.domain.user.User;

import java.util.Collection;

@RequiredArgsConstructor
public class MySocialUserDetails implements SocialUserDetails {

  private final User user;

  public Long getSocialUserId() {
    return user.getId();
  }

  @Override
  public String getUserId() {
    return user.getId().toString();
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }

  @Override
  public String getPassword() {
    return null;
  }

  @Override
  public String getUsername() {
    return null;
  }

  @Override
  public boolean isAccountNonExpired() {
    return false;
  }

  @Override
  public boolean isAccountNonLocked() {
    return false;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return false;
  }

  @Override
  public boolean isEnabled() {
    return false;
  }
}
