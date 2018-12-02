package com.sticngo.rozliczajka.domain.task;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends com.sticngo.rozliczajka.infrastructures.persistence.Repository<Task> {
    Optional<Task> findByIdAndUserId(Long taskId, Long userId);
    List<Task> findTasksByUserId(Long userId);
    List<Task> findTasksByFinishedAndUserId(Boolean status, Long userId);
    List<Task> findTasksByCategoryNameAndUserIdOrderByPositionAsc(String categoryName, Long userId);
}
