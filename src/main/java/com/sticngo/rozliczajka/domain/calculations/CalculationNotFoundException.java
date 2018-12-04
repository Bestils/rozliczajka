package com.sticngo.rozliczajka.domain.calculations;

import com.sticngo.rozliczajka.infrastructure.error.BusinessErrorCode;
import com.sticngo.rozliczajka.infrastructure.error.CommonBusinessException;

public class CalculationNotFoundException extends CommonBusinessException {

  private static final String MESSAGE = "Task with id: %s not found";

  public CalculationNotFoundException(Long id) {
    super(BusinessErrorCode.ENTITY_NOT_FOUND, String.format(MESSAGE, id));
  }
}
