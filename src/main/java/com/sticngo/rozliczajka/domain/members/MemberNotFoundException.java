package com.sticngo.rozliczajka.domain.members;

import com.sticngo.rozliczajka.infrastructure.error.BusinessErrorCode;
import com.sticngo.rozliczajka.infrastructure.error.CommonBusinessException;

public class MemberNotFoundException extends CommonBusinessException {
  private static final String MESSAGE = "Member with id: %s not found";

  public MemberNotFoundException(Long id) {
    super(BusinessErrorCode.ENTITY_NOT_FOUND, String.format(MESSAGE, id));
  }
}
