package com.tcp.iamlazy.review.validation;

import com.tcp.iamlazy.todo.validation.RepeatUnitLimit;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created with intellij IDEA. by 2020 04 2020/04/19 1:35 오후 19 User we at 13 35 To change this
 * template use File | Settings | File Templates.
 */
public class ReviewValidatonImpl implements ConstraintValidator<RepeatUnitLimit, ReviewValidation> {

  @Override
  public void initialize(RepeatUnitLimit constraintAnnotation) {

  }

  @Override
  public boolean isValid(ReviewValidation value, ConstraintValidatorContext context) {
    return value.isValid();
  }

}
