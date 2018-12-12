package com.sticngo.rozliczajka.infrastructure.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SpringSocialConfigurer;
import com.sticngo.rozliczajka.domain.user.UserRepository;
import com.sticngo.rozliczajka.infrastructure.security.MyUserDetailsService;
import com.sticngo.rozliczajka.infrastructure.security.SimpleSocialUserDetailsService;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private MyUserDetailsService userDetailsService;

  @Autowired
  private DataSource dataSource;

  @Autowired
  private UserRepository userRepository;

  @Override
  protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
    auth.authenticationProvider(authenticationProvider());
  }

  @Bean
  public DaoAuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(userDetailsService);
    authProvider.setPasswordEncoder(encoder());
    return authProvider;
  }

  @Bean
  public PasswordEncoder encoder() {
    return new BCryptPasswordEncoder(11);
  }

  @Override
  protected MyUserDetailsService userDetailsService() {
    return userDetailsService;
  }

  @Bean
  public SimpleSocialUserDetailsService simpleSocialUserDetailsService() {
    return new SimpleSocialUserDetailsService(userRepository);
  }

  @Override
  protected void configure(final HttpSecurity http) throws Exception {
    http
        .csrf().disable()
          .headers().frameOptions().disable()
        .and()
          .authorizeRequests()
          .antMatchers("/login*", "/success*").anonymous()
          .antMatchers("/auth/**", "/signup/**", "/css/*", "/webjars/**","/js/*","/image/*").permitAll()
          .anyRequest().authenticated()
        .and()
          .formLogin().loginPage("/login")
          .successForwardUrl("/calculations")
        .and()
          .logout()
          .logoutUrl("/logout")
          .logoutSuccessUrl("/logout-success").permitAll()
        .and()
          .apply(new SpringSocialConfigurer());
  }
}