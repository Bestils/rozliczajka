package com.sticngo.rozliczajka.infrastructure.web.categories;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.sticngo.rozliczajka.domain.category.Category;
import com.sticngo.rozliczajka.domain.category.CategoryService;
import com.sticngo.rozliczajka.infrastructure.security.IdProvider;

import javax.validation.Valid;

import static com.sticngo.rozliczajka.infrastructure.web.WebUtils.redirectTo;

@Slf4j
@Controller
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

  private final IdProvider idProvider;
  private final CategoryService categoryService;

  @PostMapping
  public String processCreateForm(@ModelAttribute @Valid Category category, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return redirectTo("/tasks");
    }
    categoryService.createForUser(category, idProvider.getCurrentUserId());
    return redirectTo("/tasks");
  }

}
