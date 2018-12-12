package com.sticngo.rozliczajka.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {


  private final UserRepository userRepository;

  private final PasswordEncoder passwordEncoder;

  public User getById(Long id) {
    return userRepository.findById(id)
        .orElseThrow(() -> new UserNotFoundException(id));
  }

  public void deleteById(Long id) {
    userRepository.deleteById(id);
  }

  public User createUser(User user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    return userRepository.save(user);
  }

  public List<User> findAll() {
    return userRepository.findAll();
  }

  public void updateStatus(Long userId, boolean status) {
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new UserNotFoundException(userId));
    user.setEnabled(status);
    userRepository.save(user);
  }

  public void updateUser(User user) {
    User currentInDatabase = userRepository.findById(user.getId())
        .orElseThrow(() -> new UserNotFoundException(user.getId()));

    Optional.ofNullable(user.getLogin())
        .ifPresent(currentInDatabase::setLogin);

    Optional.ofNullable(user.getPassword())
        .map(passwordEncoder::encode)
        .ifPresent(currentInDatabase::setPassword);



    Optional.ofNullable(user.getEnabled())
        .ifPresent(currentInDatabase::setEnabled);

    userRepository.save(currentInDatabase);
  }

}
