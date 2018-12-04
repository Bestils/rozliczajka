package com.sticngo.rozliczajka.infrastructure.error;

import lombok.Getter;

@Getter
public class CommonBusinessException extends RuntimeException {

  private static final String MESSAGE = "ErrorCode: %s, %s";

  private final BusinessErrorCode businessErrorCode;

  public CommonBusinessException(BusinessErrorCode businessErrorCode) {
    super(businessErrorCode.getMessage());
    this.businessErrorCode = businessErrorCode;
  }

  public CommonBusinessException(BusinessErrorCode businessErrorCode, String message) {
    super(String.format(MESSAGE, businessErrorCode.name(), message));
    this.businessErrorCode = businessErrorCode;
  }
}
