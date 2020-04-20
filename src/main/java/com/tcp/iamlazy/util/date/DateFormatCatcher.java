package com.tcp.iamlazy.util.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * 사용자의 입력을 받은 날짜 문자열을 지정된 포맷에 해당하는 양식으로 파싱하는 기능을 제공한다.
 *
 * Created with intellij IDEA. by 2020 04 2020/04/19 12:56 오후 19 User we at 12 56 To change this
 * template use File | Settings | File Templates.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public class DateFormatCatcher {

  /**
   * 주어진 문자열 데이터를 포맷 양식으로 변환 시도한다.
   * @param dateString
   * @param format
   * @return 포맷 변환 실패 시, 현재 날짜에 해당하는 데이터를 가져온다.
   */
  public static LocalDateTime getLocalDateTime(String dateString, String formatString) {
    SimpleDateFormat format = new SimpleDateFormat(formatString);
    try {
      return LocalDateTime.ofInstant(format.parse(dateString).toInstant(),
                                              ZoneId.systemDefault());
    } catch (ParseException e) {
      log.error("Parameter date can't be parsed.. check valid format yyyyMMdd : {}", e.getMessage());
      return LocalDateTime.now();
    }
  }

}
