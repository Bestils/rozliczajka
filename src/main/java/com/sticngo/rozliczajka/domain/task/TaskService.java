package com.sticngo.rozliczajka.domain.task;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.transaction.annotation.Transactional;
import com.sticngo.rozliczajka.domain.category.CategoryService;
import com.sticngo.rozliczajka.domain.user.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static java.lang.Math.toIntExact;

@Slf4j
@Transactional
@RequiredArgsConstructor
public class TaskService {

  private final TaskRepository taskRepository;
  private final UserService userService;
  private final CategoryService categoryService;

  public List<Task> findTasksBelongToUser(Long idUser) {
    return taskRepository.findTasksByUserId(idUser);
  }

  @PostAuthorize("returnObject.user.login == authentication.name")
  public Task findById(Long id) {
    return taskRepository.findById(id)
        .orElseThrow(() -> new TaskNotFoundException(id));
  }

  public List<Task> findByStatus(Boolean status, Long userId) {
    return taskRepository.findTasksByFinishedAndUserId(status, userId);
  }

  public Task save(Task task, Long userId) {
    task.setUser(userService.getById(userId));
    return taskRepository.save(task);
  }

  public void deleteById(Long taskId, Long userId) {
    taskRepository.findByIdAndUserId(taskId, userId)
        .orElseThrow(() -> new TaskNotFoundException(taskId));

    taskRepository.deleteById(taskId);
  }

  public void updateTask(Task task, Long userId) {
    Task that = taskRepository.findByIdAndUserId(task.getId(), userId)
        .orElseThrow(() -> new TaskNotFoundException(task.getId()));

    Optional.ofNullable(task.getName())
        .ifPresent(that::setName);

    Optional.ofNullable(task.getDescription())
        .ifPresent(that::setDescription);

    Optional.ofNullable(task.getPriority())
        .ifPresent(that::setPriority);

    Optional.ofNullable(task.getFinished())
        .ifPresent(that::setFinished);

    save(that, userId);
  }

  public Task createForUser(@Valid Task task, Long currentUserId) {
    task.setUser(userService.getById(currentUserId));
    task.setPosition(getNextPositionForTask(task.getCategory().getName(), currentUserId));
    return taskRepository.save(task);
  }

  private int getNextPositionForTask(String categoryName, Long userId) {
    List<Task> tasks = taskRepository.findTasksByCategoryNameAndUserIdOrderByPositionAsc(categoryName, userId);
    return tasks.size() != 0 ? (tasks.get(tasks.size() - 1).getPosition() + 1) : 0;
  }

  public void changeCategory(Long taskId, Long categoryId, Long positionInCategory) {
    Task that = taskRepository.findById(taskId)
        .orElseThrow(() -> new TaskNotFoundException(taskId));
    that.updateCategory(categoryService.findById(categoryId), toIntExact(positionInCategory));
  }
}