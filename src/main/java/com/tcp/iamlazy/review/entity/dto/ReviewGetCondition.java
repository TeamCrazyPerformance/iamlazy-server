package com.tcp.iamlazy.review.entity.dto;

import java.time.LocalDateTime;
import lombok.Data;

/**
 * Created with intellij IDEA. by 2020 04 2020/04/19 1:04 오후 19 User we at 13 04 To change this
 * template use File | Settings | File Templates.
 */
@Data
public class ReviewGetCondition {

  private final String userName;
  private final LocalDateTime localDateTime;

}
