package com.sticngo.rozliczajka.infrastructure.security;

import lombok.extern.slf4j.Slf4j;
import com.sticngo.rozliczajka.infrastructure.error.CommonTechnicalException;
import com.sticngo.rozliczajka.infrastructure.error.TechnicalErrorCode;


@Slf4j
public class UserNotAuthenticatedException extends CommonTechnicalException {

  UserNotAuthenticatedException() {
    super(TechnicalErrorCode.NOT_AUTHENTICATED);
  }
}
