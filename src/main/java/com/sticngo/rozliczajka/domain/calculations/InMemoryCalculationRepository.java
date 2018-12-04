package com.sticngo.rozliczajka.domain.calculations;

import com.sticngo.rozliczajka.infrastructure.persistence.AbstractInMemoryRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Simple TaskRepository implementation for unit testing
 */
public class InMemoryCalculationRepository
    extends AbstractInMemoryRepository<Calculation>
    implements CalculationRepository {

  @Override
  public Optional<Calculation> findByIdAndUserId(Long taskId, Long userId) {
    return findById(taskId)
        .filter(task -> task.getUser() != null)
        .filter(task -> task.getUser().getId().equals(userId));
  }

  @Override
  public List<Calculation> findTasksByUserId(Long userId) {
    return findAll().stream()
        .filter(task -> task.getUser() != null)
        .filter(task -> task.getUser().getId().equals(userId))
        .collect(Collectors.toList());
  }

  @Override
  public List<Calculation> findTasksByFinishedAndUserId(Boolean status, Long userId) {
    return findAll().stream()
        .filter(task -> task.getUser() != null)
        .filter(task -> task.getUser().getId().equals(userId))
        .filter(task -> task.getFinished().equals(status))
        .collect(Collectors.toList());
  }


  @Override
  public List<Calculation> findTasksByMemberNameAndUserIdOrderByPositionAsc(String memberName, Long userId) {
    return findAll().stream()
        .filter(task -> task.getMember().getName() == memberName)
        .filter(task -> task.getUser().getId() == userId)
        .collect(Collectors.toList());
  }
}
