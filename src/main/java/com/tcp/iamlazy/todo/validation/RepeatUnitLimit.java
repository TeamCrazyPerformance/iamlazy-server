package com.tcp.iamlazy.todo.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {TodoRepeatUnitValidator.class})
public @interface RepeatUnitLimit {
  String message() default "유효한 값이 아닙니다. 유효값 0, 1, 7, 30";  // 애노테이션 지정 시 validatino rule에 맞는 메시지 지정 가능!!!

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
