package com.sticngo.rozliczajka.infrastructure.web.tasks;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.sticngo.rozliczajka.domain.category.Category;
import com.sticngo.rozliczajka.domain.category.CategoryService;
import com.sticngo.rozliczajka.domain.task.Task;
import com.sticngo.rozliczajka.domain.task.TaskService;
import com.sticngo.rozliczajka.infrastructure.security.IdProvider;

import javax.validation.Valid;

import static com.sticngo.rozliczajka.infrastructure.web.WebUtils.redirectTo;
import static com.sticngo.rozliczajka.infrastructure.web.tasks.TaskViews.*;


@Slf4j
@Controller
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

  private final TaskService taskService;
  private final CategoryService categoryService;
  private final IdProvider idProvider;

  @GetMapping
  public String findAll(Model model) {
    model.addAttribute("categories", categoryService.findCategoriesByUserId(idProvider.getCurrentUserId()));
    model.addAttribute("task", new Task());
    model.addAttribute("category", new Category());
    return TASKS_ALL;
  }

  @GetMapping("{id}")
  public String getTaskDetails(@PathVariable Long id, Model model) {
    model.addAttribute("task", taskService.findById(id));
    return TASK_DETAILS;
  }

  @GetMapping("edit")
  public String addEditForm(Model model) {
    model.addAttribute("task", new Task());
    return TASK_EDIT_FORM;
  }

  @PostMapping
  public String processCreateForm(@ModelAttribute @Valid Task task, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return redirectTo("/tasks");
    }
    taskService.createForUser(task, idProvider.getCurrentUserId());
    return redirectTo("/tasks");
  }

  @PostMapping("update")
  public String processUpdateForm(@ModelAttribute @Valid Task task, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return TASK_EDIT_FORM;
    }
    taskService.updateTask(task, idProvider.getCurrentUserId());
    return redirectTo("/tasks");
  }

  @PostMapping("delete")
  public String deleteTask(@RequestBody Long id) {
    taskService.deleteById(id, idProvider.getCurrentUserId());
    return redirectTo(TASKS_ALL);
  }
}
