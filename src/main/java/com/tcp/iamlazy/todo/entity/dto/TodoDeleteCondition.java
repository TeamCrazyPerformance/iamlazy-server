package com.tcp.iamlazy.todo.entity.dto;

import lombok.Data;

/**
 * Created with intellij IDEA. by 2020 03 29/03/2020 6:03 오후 29 User we at 18 03 To change this
 * template use File | Settings | File Templates.
 */
@Data
public class TodoDeleteCondition {

  private final int userId;
  private final int todoIdx;

}
