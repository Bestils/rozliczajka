package com.sticngo.rozliczajka.infrastructures.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;
import javax.persistence.OptimisticLockException;


@Slf4j
@ControllerAdvice
class ExceptionHandlingAdvice {

  @ExceptionHandler(CommonTechnicalException.class)
  ResponseEntity<ErrorResponse> handleCommonTechnicalError(CommonTechnicalException e) {
    return new ResponseEntity<>(
        new ErrorResponse(e.getMessage(), e.getClass().getName()),
        e.getTechnicalErrorCode().getHttpStatus());
  }

  @ExceptionHandler(CommonBusinessException.class)
  ResponseEntity<ErrorResponse> handleCommonTechnicalError(CommonBusinessException e) {
    return new ResponseEntity<>(
        new ErrorResponse(e.getMessage(), e.getClass().getName()),
        e.getBusinessErrorCode().getHttpStatus());
  }

  @ExceptionHandler(EntityNotFoundException.class)
  ResponseEntity<?> handleNotFound(EntityNotFoundException e) {
    return ResponseEntity.notFound().build();
  }

  @ExceptionHandler(IllegalArgumentException.class)
  ResponseEntity<?> handleIllegalArgument(IllegalArgumentException e) {
    return ResponseEntity.badRequest().build();
  }

  @ExceptionHandler(OptimisticLockException.class)
  ResponseEntity<?> handleOptimisticLock(OptimisticLockException e) {
    return ResponseEntity.status(HttpStatus.CONFLICT).build();
  }

  @Data
  @AllArgsConstructor
  private static class ErrorResponse {

    private final String message;
    private final String cause;
  }
}
