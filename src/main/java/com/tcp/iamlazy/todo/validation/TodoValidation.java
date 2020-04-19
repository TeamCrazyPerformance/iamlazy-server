package com.tcp.iamlazy.todo.validation;

public interface TodoValidation {

  /**
   * @see com.sun.tools.javac.comp.Todo 클래스의 repeatableUnit 의 값을 체크한다.
   *
   * 이 외 전체적인 타당성 검증을 수행할 수 있으나 해당은 별도 메서드를 추가해도 충분하다.
   *
   * @return
   */
  boolean isRepeatUnitValid();

}
