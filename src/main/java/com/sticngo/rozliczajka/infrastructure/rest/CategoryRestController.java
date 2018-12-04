package com.sticngo.rozliczajka.infrastructure.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.sticngo.rozliczajka.domain.category.Category;
import com.sticngo.rozliczajka.domain.category.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryRestController {

  private final CategoryService categoryService;

  @GetMapping
  public List<Category> findAll() {
    return categoryService.findAll();
  }

  @GetMapping("/{id}")
  public Category getCategoryById(@PathVariable Long id) {
    return categoryService.findById(id);
  }

  @PostMapping
  public Category addCategory(@RequestBody Category category) {
    return categoryService.save(category);
  }

  @PatchMapping
  public void updateCategory(@RequestBody Category category) {
    categoryService.updateCategory(category);
  }

  @DeleteMapping("{id}")
  public void deleteCategory(@PathVariable Long id) {
      categoryService.deleteById(id);
  }
}
