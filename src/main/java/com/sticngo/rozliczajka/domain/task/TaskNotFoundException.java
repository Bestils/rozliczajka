package com.sticngo.rozliczajka.domain.task;

import com.sticngo.rozliczajka.infrastructures.error.BusinessErrorCode;
import com.sticngo.rozliczajka.infrastructures.error.CommonBusinessException;

public class TaskNotFoundException extends CommonBusinessException {

  private static final String MESSAGE = "Task with id: %s not found";

  public TaskNotFoundException(Long id) {
    super(BusinessErrorCode.ENTITY_NOT_FOUND, String.format(MESSAGE, id));
  }
}
