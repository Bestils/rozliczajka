package com.sticngo.rozliczajka.infrastructure.error;

public class NotImplementedException extends CommonTechnicalException {

  public NotImplementedException() {
    super(TechnicalErrorCode.NOT_IMPLEMENTED);
  }
}
