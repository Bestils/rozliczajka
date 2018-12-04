package com.sticngo.rozliczajka.domain.calculations;

import com.sticngo.rozliczajka.domain.category.CategoryService;
import com.sticngo.rozliczajka.domain.members.MemberService;
import com.sticngo.rozliczajka.domain.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static java.lang.Math.toIntExact;

@Slf4j
@Transactional
@RequiredArgsConstructor
public class CalculationService {

  private final CalculationRepository calculationRepository;
  private final UserService userService;
  private final MemberService memberService;

  public List<Calculation> findTasksBelongToUser(Long idUser) {
    return calculationRepository.findTasksByUserId(idUser);
  }

  @PostAuthorize("returnObject.user.login == authentication.name")
  public Calculation findById(Long id) {
    return calculationRepository.findById(id)
        .orElseThrow(() -> new CalculationNotFoundException(id));
  }

  public List<Calculation> findByStatus(Boolean status, Long userId) {
    return calculationRepository.findTasksByFinishedAndUserId(status, userId);
  }

  public Calculation save(Calculation task, Long userId) {
    task.setUser(userService.getById(userId));
    return calculationRepository.save(task);
  }

  public void deleteById(Long taskId, Long userId) {
    calculationRepository.findByIdAndUserId(taskId, userId)
        .orElseThrow(() -> new CalculationNotFoundException(taskId));

    calculationRepository.deleteById(taskId);
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

  public Calculation createForUser(@Valid Calculation calculation, Long currentUserId) {
    calculation.setUser(userService.getById(currentUserId));
    calculation.setPosition(getNextPositionForTask(calculation.getMember().getName(), currentUserId));
    return calculationRepository.save(calculation);
  }

  private int getNextPositionForTask(String categoryName, Long userId) {
    List<Calculation> tasks = calculationRepository.findTasksByMemberNameAndUserIdOrderByPositionAsc(categoryName, userId);
    return tasks.size() != 0 ? (tasks.get(tasks.size() - 1).getPosition() + 1) : 0;
  }

//  public void changeCategory(Long taskId, Long categoryId, Long positionInCategory) {
//    Calculation that = calculationRepository.findById(taskId)
//        .orElseThrow(() -> new CalculationNotFoundException(taskId));
//    that.updateCategory(categoryService.findById(categoryId), toIntExact(positionInCategory));
//  }
}