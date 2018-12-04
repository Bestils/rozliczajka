package com.sticngo.rozliczajka.domain.category;

import com.sticngo.rozliczajka.infrastructure.error.BusinessErrorCode;
import com.sticngo.rozliczajka.infrastructure.error.CommonBusinessException;

public class CategoryNotFoundException extends CommonBusinessException {
  private static final String MESSAGE = "Category with id: %s not found";

  public CategoryNotFoundException(Long id) {
    super(BusinessErrorCode.ENTITY_NOT_FOUND, String.format(MESSAGE, id));
  }
}
