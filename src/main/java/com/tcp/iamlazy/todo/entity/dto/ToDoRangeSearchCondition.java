package com.tcp.iamlazy.todo.entity.dto;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import lombok.Data;

/**
 * Created with intellij IDEA. by 2020 03 29/03/2020 2:46 오후 29 User we at 14 46 To change this
 * template use File | Settings | File Templates.
 */
@Data
public class ToDoRangeSearchCondition {

  private final String userName;
  private final LocalDateTime localDateTime;

  public String getLocalDateTime() {
    SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
    return format.format(Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant()));
  }

}
