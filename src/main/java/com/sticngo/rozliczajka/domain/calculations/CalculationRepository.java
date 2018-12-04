package com.sticngo.rozliczajka.domain.calculations;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CalculationRepository extends com.sticngo.rozliczajka.infrastructure.persistence.Repository<Calculation> {
    Optional<Calculation> findByIdAndUserId(Long taskId, Long userId);
    List<Calculation> findTasksByUserId(Long userId);
    List<Calculation> findTasksByFinishedAndUserId(Boolean status, Long userId);
    List<Calculation> findTasksByMemberNameAndUserIdOrderByPositionAsc(String memberName, Long userId);
}
