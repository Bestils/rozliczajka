package com.sticngo.rozliczajka.domain.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.sticngo.rozliczajka.domain.user.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

  private final CategoryRepository categoryRepository;
  private final UserService userService;

  public List<Category> findAll() {
    return categoryRepository.findAll();
  }

  public Category findById(Long id) {
    return categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException(id));
  }

  public List<Category> findCategoriesByUserId(Long id) {
    return categoryRepository.findCategoriesByUserId(id);
  }

  public Category save(Category category) {
    return categoryRepository.save(category);
  }

  public void updateCategory(Category category) {
    Category existCategory = categoryRepository.findById(category.getId())
        .orElseThrow(() -> new CategoryNotFoundException(category.getId()));

    Optional.ofNullable(category.getName())
        .ifPresent(existCategory::setName);

    save(category);
  }

  public void deleteById(Long id) {
    categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException(id));
    categoryRepository.deleteById(id);
  }

  public Category createForUser(@Valid Category category, Long currentUserId) {
    category.setUser(userService.getById(currentUserId));
    return categoryRepository.save(category);
  }
}