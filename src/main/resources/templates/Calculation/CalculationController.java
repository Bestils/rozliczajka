package pl.java.learning.todolist.infrastructure.web.tasks;

import static pl.java.learning.todolist.infrastructure.web.WebUtils.redirectTo;
import static pl.java.learning.todolist.infrastructure.web.tasks.TaskViews.TASKS_ALL;
import static pl.java.learning.todolist.infrastructure.web.tasks.TaskViews.TASK_DETAILS;
import static pl.java.learning.todolist.infrastructure.web.tasks.TaskViews.TASK_EDIT_FORM;

import java.security.Principal;
import java.util.Optional;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.java.learning.todolist.domain.category.Category;
import pl.java.learning.todolist.domain.category.CategoryService;
import pl.java.learning.todolist.domain.task.Task;
import pl.java.learning.todolist.domain.task.TaskService;
import pl.java.learning.todolist.domain.user.User;
import pl.java.learning.todolist.domain.user.UserService;
import pl.java.learning.todolist.infrastructure.security.IdProvider;
import pl.java.learning.todolist.infrastructure.security.MyUserDetailsService;


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
