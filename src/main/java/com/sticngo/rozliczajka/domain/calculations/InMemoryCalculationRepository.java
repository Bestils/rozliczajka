package com.sticngo.rozliczajka.domain.calculations;

import com.sticngo.rozliczajka.infrastructure.persistence.AbstractInMemoryRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Simple calculationRepository implementation for unit testing
 */
public class InMemoryCalculationRepository
    extends AbstractInMemoryRepository<Calculation>
    implements CalculationRepository {

  @Override
  public Optional<Calculation> findByIdAndUserId(Long calculationId, Long userId) {
    return findById(calculationId)
        .filter(calculation -> calculation.getUser() != null)
        .filter(calculation -> calculation.getUser().getId().equals(userId));
  }

  @Override
  public List<Calculation> findcalculationsByUserId(Long userId) {
    return findAll().stream()
        .filter(calculation -> calculation.getUser() != null)
        .filter(calculation -> calculation.getUser().getId().equals(userId))
        .collect(Collectors.toList());
  }

  @Override
  public List<Calculation> findcalculationsByFinishedAndUserId(Boolean status, Long userId) {
    return findAll().stream()
        .filter(calculation -> calculation.getUser() != null)
        .filter(calculation -> calculation.getUser().getId().equals(userId))
        .filter(calculation -> calculation.getFinished().equals(status))
        .collect(Collectors.toList());
  }

  @Override
  public List<Calculation> findcalculationsByMemberNameAndUserIdOrderByPositionAsc(String memberName, Long userId) {
    return null;
  }







//  @Override
//  public List<Calculation> findcalculationsByMemberNameAndUserIdOrderByPositionAsc(String memberName, Long userId) {
//    return findAll().stream()
//        .filter(calculation -> calculation.getMembers().stream()
//
//               . filter(members -> members.getFirstName().
//               . filter(members -> members.getLastName().
//
//
//
//                getName() == memberName)
//        .filter(calculation -> calculation.getUser().getId() == userId)
//        .collect(Collectors.toList());
//  }
}
