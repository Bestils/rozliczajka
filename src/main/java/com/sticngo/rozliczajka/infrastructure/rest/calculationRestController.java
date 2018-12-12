package com.sticngo.rozliczajka.infrastructure.rest;

import com.sticngo.rozliczajka.domain.calculations.Calculation;
import com.sticngo.rozliczajka.domain.calculations.CalculationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import com.sticngo.rozliczajka.infrastructure.security.IdProvider;

import java.util.List;

@RestController
@RequestMapping("/api/calculations")
@RequiredArgsConstructor
public class calculationRestController {

  private final CalculationService calculationService;
  private final IdProvider idProvider;

  @GetMapping
  public List<Calculation> findcalculationsByUserId() {
    return calculationService.findcalculationsBelongToUser(idProvider.getCurrentUserId());
  }

  @GetMapping("{id}")
  public Calculation getcalculationAboutId(@PathVariable Long id) {
    return calculationService.findById(id);
  }

  @GetMapping("/status")
  public List<Calculation> getFinishedcalculations(@RequestParam(value = "finished", defaultValue = "true") Boolean status) {
    return calculationService.findByStatus(status, idProvider.getCurrentUserId());
  }

  @PostMapping
  public Calculation addcalculation(@RequestBody Calculation calculation) {
    return calculationService.save(calculation, idProvider.getCurrentUserId());
  }

  @PatchMapping
  public void updatecalculation(@RequestBody Calculation calculation) {
    calculationService.updateCalculation(calculation, idProvider.getCurrentUserId());
  }

  @DeleteMapping("{id}")
  public void deletecalculation(@PathVariable Long id) {
      calculationService.deleteById(id, idProvider.getCurrentUserId());
  }

//  @PostMapping("/change")
//  public void changePositionInLists(@RequestParam("calculationId") Long calculationId,
//      @RequestParam("newPositionInList") Long newPositionInList,
//      @RequestParam("newCategoryId") Long newCategoryId) {
//    calculationService.updateCalculation(calculationId, newCategoryId, newPositionInList);
//  }
}