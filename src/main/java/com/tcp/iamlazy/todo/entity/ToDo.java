package com.tcp.iamlazy.todo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tcp.iamlazy.todo.validation.RepeatUnitLimit;
import com.tcp.iamlazy.todo.validation.TodoValidation;
import java.util.Date;
import java.util.Objects;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

@Getter @Setter
@ToString
@RepeatUnitLimit
public class ToDo implements TodoValidation {
    @NotNull(message = "userIdx cannot be null.")
    private int userId;
    @NotNull(message = "todoIdx cannot be null.")
    private int todoIdx;
    private String todoTitle;
    private String todoContent;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date todoDate;
    private boolean repeatableYN;
    private int repeatUnit;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;
    @DecimalMax(value = "6")
    private String weekDay;
    @DecimalMax(value = "31")
    private int monthDay;
    private boolean finish;

    @Override
    @JsonIgnore
    public boolean isRepeatUnitValid() {
        if (Objects.isNull(repeatableYN) && !this.repeatableYN) {
            if (this.repeatUnit == 0 ) {
                return true;
            }
            if (Objects.isNull(this.repeatUnit)) {
                return true;
            } else {
                return false;
            }
        } else if (Objects.isNull(this.repeatUnit)) {
            return false;
        } else if (repeatUnit == 1 || repeatUnit == 7 || repeatUnit == 30) {
            return true;
        } else {
            return false;
        }
    }
}
