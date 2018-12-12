package com.sticngo.rozliczajka.domain.calculations;


import com.sticngo.rozliczajka.domain.members.Member;
import com.sticngo.rozliczajka.domain.members.MemberService;
import com.sticngo.rozliczajka.domain.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static java.lang.Math.toIntExact;

@Service
@RequiredArgsConstructor
public class CalculationService {

  private final CalculationRepository calculationRepository;
  private final UserService userService;
  private final MemberService memberService;

  public List<Calculation> findcalculationsBelongToUser(Long idUser) {
    return calculationRepository.findcalculationsByUserId(idUser);
  }

  @PostAuthorize("returnObject.user.login == authentication.name")
  public Calculation findById(Long id) {
    return calculationRepository.findById(id)
        .orElseThrow(() -> new CalculationNotFoundException(id));
  }

  public List<Calculation> findByStatus(Boolean status, Long userId) {
    return calculationRepository.findcalculationsByFinishedAndUserId(status, userId);
  }

  public Calculation save(Calculation calculation, Long userId) {
    calculation.setMemberOwner(memberService.getById(userId));
    return calculationRepository.save(calculation);
  }

  public void deleteById(Long calculationId, Long userId) {
    calculationRepository.findByIdAndUserId(calculationId, userId)
        .orElseThrow(() -> new CalculationNotFoundException(calculationId));

    calculationRepository.deleteById(calculationId);
  }




  public void updateCalculation(Calculation calculation, Long userId) {
    Calculation that = calculationRepository.findByIdAndUserId(calculation.getId(), userId)
        .orElseThrow(() -> new CalculationNotFoundException(calculation.getId()));

    Optional.ofNullable(calculation.getName())
        .ifPresent(that::setName);

    Optional.ofNullable(calculation.getDescription())
        .ifPresent(that::setDescription);



    Optional.ofNullable(calculation.getFinished())
        .ifPresent(that::setFinished);

    save(that, userId);
  }

//  public Calculation createForUser(@Valid Calculation calculation, Long currentUserId) {
//    calculation.setUser(userService.getById(currentUserId));
//    calculation.setPosition(getNextPositionForcalculation(calculation.getMembers().getName(), currentUserId));
//    return calculationRepository.save(calculation);
//  }

  private int getNextPositionForcalculation(String categoryName, Long userId) {
    List<Calculation> calculations = calculationRepository.findcalculationsByMemberNameAndUserIdOrderByPositionAsc(categoryName, userId);
    return calculations.size() != 0 ? (calculations.get(calculations.size() - 1).getPosition() + 1) : 0;
  }

  public Set<Member> getMembers(Calculation calculation){

    return  calculation.getMembers();

  }

}

//  public void changeCategory(Long calculationId, Long categoryId, Long positionInCategory) {
//    Calculation that = calculationRepository.findById(calculationId)
//        .orElseThrow(() -> new CalculationNotFoundException(calculationId));
//    that.updateCategory(categoryService.findById(categoryId), toIntExact(positionInCategory));
//  }
