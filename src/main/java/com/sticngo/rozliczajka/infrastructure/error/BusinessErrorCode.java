package com.sticngo.rozliczajka.infrastructure.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum BusinessErrorCode {

  GENERAL_ERROR("General error", HttpStatus.INTERNAL_SERVER_ERROR),
  ENTITY_NOT_FOUND("Entity not found", HttpStatus.NOT_FOUND);

  private final String message;
  private final HttpStatus httpStatus;

}
