package com.sticngo.rozliczajka.domain.calculations;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CalculationRepository extends com.sticngo.rozliczajka.infrastructure.persistence.Repository<Calculation> {
    Optional<Calculation> findByIdAndUserId(Long calculationId, Long userId);
    List<Calculation> findcalculationsByUserId(Long userId);
    List<Calculation> findcalculationsByFinishedAndUserId(Boolean status, Long userId);
    List<Calculation> findcalculationsByMemberNameAndUserIdOrderByPositionAsc(String memberName, Long userId);
}
