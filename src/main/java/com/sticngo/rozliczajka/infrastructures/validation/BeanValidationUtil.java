package com.sticngo.rozliczajka.infrastructures.validation;

import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Objects;
import java.util.Set;


@Slf4j
public class BeanValidationUtil {

  private static final Validator validator = Validation
      .buildDefaultValidatorFactory()
      .getValidator();


  public static void validate(Object bean) {
    Set<ConstraintViolation<Object>> violations = violations(bean);

    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }
  }

  public static boolean isValid(Object bean) {
    return violations(bean).isEmpty();
  }

  public static <T> Set<ConstraintViolation<T>> violations(T bean) {
    Objects.requireNonNull(bean);
    return validator.validate(bean);
  }

}
