package com.tcp.iamlazy.todo.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created with intellij IDEA. by 2020 04 2020/04/09 12:24 오전 09 User we at 00 24 To change this
 * template use File | Settings | File Templates.
 */
public class TodoRepeatUnitValidator implements ConstraintValidator<RepeatUnitLimit, TodoValidation> {

  @Override
  public void initialize(RepeatUnitLimit constraintAnnotation) {

  }

  @Override
  public boolean isValid(TodoValidation value, ConstraintValidatorContext context) {
    return value.isRepeatUnitValid();
  }
}
