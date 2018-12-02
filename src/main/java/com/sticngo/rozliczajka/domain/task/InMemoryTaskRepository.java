package com.sticngo.rozliczajka.domain.task;

import com.sticngo.rozliczajka.infrastructures.persistence.AbstractInMemoryRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Simple TaskRepository implementation for unit testing
 */
public class InMemoryTaskRepository
    extends AbstractInMemoryRepository<Task>
    implements TaskRepository {

  @Override
  public Optional<Task> findByIdAndUserId(Long taskId, Long userId) {
    return findById(taskId)
        .filter(task -> task.getUser() != null)
        .filter(task -> task.getUser().getId().equals(userId));
  }

  @Override
  public List<Task> findTasksByUserId(Long userId) {
    return findAll().stream()
        .filter(task -> task.getUser() != null)
        .filter(task -> task.getUser().getId().equals(userId))
        .collect(Collectors.toList());
  }

  @Override
  public List<Task> findTasksByFinishedAndUserId(Boolean status, Long userId) {
    return findAll().stream()
        .filter(task -> task.getUser() != null)
        .filter(task -> task.getUser().getId().equals(userId))
        .filter(task -> task.getFinished().equals(status))
        .collect(Collectors.toList());
  }

  @Override
  public List<Task> findTasksByCategoryNameAndUserIdOrderByPositionAsc(String categoryName, Long userId) {
    return findAll().stream()
        .filter(task -> task.getCategory().getName() == categoryName)
        .filter(task -> task.getUser().getId() == userId)
        .collect(Collectors.toList());
  }
}
