package com.sticngo.rozliczajka.infrastructure.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.sticngo.rozliczajka.domain.task.Task;
import com.sticngo.rozliczajka.domain.task.TaskService;
import com.sticngo.rozliczajka.infrastructure.security.IdProvider;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskRestController {

  private final TaskService taskService;
  private final IdProvider idProvider;

  @GetMapping
  public List<Task> findTasksByUserId() {
    return taskService.findTasksBelongToUser(idProvider.getCurrentUserId());
  }

  @GetMapping("{id}")
  public Task getTaskAboutId(@PathVariable Long id) {
    return taskService.findById(id);
  }

  @GetMapping("/status")
  public List<Task> getFinishedTasks(@RequestParam(value = "finished", defaultValue = "true") Boolean status) {
    return taskService.findByStatus(status, idProvider.getCurrentUserId());
  }

  @PostMapping
  public Task addTask(@RequestBody Task task) {
    return taskService.save(task, idProvider.getCurrentUserId());
  }

  @PatchMapping
  public void updateTask(@RequestBody Task task) {
    taskService.updateTask(task, idProvider.getCurrentUserId());
  }

  @DeleteMapping("{id}")
  public void deleteTask(@PathVariable Long id) {
      taskService.deleteById(id, idProvider.getCurrentUserId());
  }

  @PostMapping("/change")
  public void changePositionInLists(@RequestParam("taskId") Long taskId,
      @RequestParam("newPositionInList") Long newPositionInList,
      @RequestParam("newCategoryId") Long newCategoryId) {
    taskService.changeCategory(taskId, newCategoryId, newPositionInList);
  }
}