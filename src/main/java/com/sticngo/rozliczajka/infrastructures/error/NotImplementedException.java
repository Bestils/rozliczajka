package com.sticngo.rozliczajka.infrastructures.error;

public class NotImplementedException extends CommonTechnicalException {

  public NotImplementedException() {
    super(TechnicalErrorCode.NOT_IMPLEMENTED);
  }
}
