package com.sticngo.rozliczajka.infrastructures.security;

import lombok.extern.slf4j.Slf4j;
import com.sticngo.rozliczajka.infrastructures.error.CommonTechnicalException;
import com.sticngo.rozliczajka.infrastructures.error.TechnicalErrorCode;


@Slf4j
public class UserNotAuthenticatedException extends CommonTechnicalException {

  UserNotAuthenticatedException() {
    super(TechnicalErrorCode.NOT_AUTHENTICATED);
  }
}
