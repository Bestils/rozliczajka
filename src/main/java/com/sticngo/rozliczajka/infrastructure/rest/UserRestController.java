package com.sticngo.rozliczajka.infrastructure.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.sticngo.rozliczajka.domain.user.User;
import com.sticngo.rozliczajka.domain.user.UserService;
import com.sticngo.rozliczajka.infrastructure.security.IdProvider;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserRestController {

  private final UserService userService;
  private final IdProvider idProvider;

  @GetMapping
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public List<User> findAll() {
    return userService.findAll();
  }

  @PostMapping
  public User addUser(@RequestBody User user) {
    return userService.createUser(user);
  }

  @PatchMapping
  public void updateUser(@RequestBody User user) {
    if(idProvider.getCurrentUserId() == user.getId()) {
      userService.updateUser(user);
    }
  }

  @GetMapping("/{id}")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public User getUserById(@PathVariable Long id) {
    return userService.getById(id);
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public void deleteUserById(@PathVariable Long id) {
    userService.deleteById(id);
  }
}