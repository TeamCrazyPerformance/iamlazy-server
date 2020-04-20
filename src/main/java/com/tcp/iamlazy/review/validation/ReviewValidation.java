package com.tcp.iamlazy.review.validation;

public interface ReviewValidation {

  /**
   * @see com.tcp.iamlazy.review.entity.Review 클래스의 값을 체크한다.
   *
   * 이 외 전체적인 타당성 검증을 수행할 수 있으나 해당은 별도 메서드를 추가해도 충분하다.
   *
   * @return
   */
  /* fixme : 테스트를 할 때마다 공통적으로 나나타는 코드이다. 이걸 어디에 일원화를 해볼 수 있을까.. */
  boolean isValid();
}
