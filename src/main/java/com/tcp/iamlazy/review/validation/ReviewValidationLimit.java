package com.tcp.iamlazy.review.validation;

import com.tcp.iamlazy.todo.validation.TodoRepeatUnitValidator;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {TodoRepeatUnitValidator.class})
public @interface ReviewValidationLimit {

}
