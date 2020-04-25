package com.tcp.iamlazy.todo.entity;

import static com.tcp.iamlazy.util.date.DateFormatCatcher.getDataStringInyyyyMMdd;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.tcp.iamlazy.todo.validation.RepeatUnitLimit;
import com.tcp.iamlazy.todo.validation.TodoValidation;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModelProperty.AccessMode;
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
    @ApiModelProperty(accessMode = AccessMode.READ_ONLY, example = "이 필드는 지우세요")
    @JsonProperty(access = Access.READ_ONLY)
    private int userId;
    @JsonProperty(access = Access.READ_ONLY)
    @NotNull(message = "todoIdx cannot be null.")
    @ApiModelProperty(accessMode = AccessMode.READ_ONLY, example = "이 필드는 지우세요")
    private int todoIdx;
    @ApiModelProperty(value = "title text", name = "todoTitle", dataType = "string", example = "What to do today", required = true, allowEmptyValue = true)
    private String todoTitle;
    @ApiModelProperty(value = "content text", name = "todoContent", dataType = "string", example = "Today I have to study.", required = true, allowEmptyValue = true)
    private String todoContent;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "registration date", name = "todoDate", dataType = "string", example = "2020-06-04", required = true)
    @JsonIgnore
    private Date todoDate;
    @ApiModelProperty(value = "true|false", name = "repeatableYN", dataType = "boolean", example = "true | false", required = true, allowableValues = "{true, false}")
    private boolean repeatableYN;
    @ApiModelProperty(value = "repeatUnit(only required when repeatableYN is true", name = "repeatUnit", dataType = "integer", example = "0|1|7|30", required = false, allowableValues = "{0, 1, 7, 30}")
    private int repeatUnit;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "startDate(only required when repeatableYN is true", name = "startDate", dataType = "string", example = "2020-07-04", required = false)
    @JsonIgnore
    private Date startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "endDate(only required when repeatableYN is true", name = "endDate", dataType = "string", example = "2020=08-09", required = false)
    @JsonIgnore
    private Date endDate;
    @DecimalMax(value = "6")
    @ApiModelProperty(value = "weekDay(only required when repeatableYN is true", name = "weekDay", dataType = "integer", example = "0~6", required = false, allowableValues = "range(0, 7)")
    private int weekDay;
    @DecimalMax(value = "31")
    @ApiModelProperty(value = "monthDay(only required when repeatableYN is true", name = "monthDay", dataType = "integer", example = "1~31", required = false, allowableValues = "range(1, 32)")
    private int monthDay;
    @ApiModelProperty(value = "finish(only required when repeatableYN is true", name = "finish", dataType = "integer", example = "true|false", required = false)
    private boolean finish;

    @JsonProperty("todoDate")
    public String getTodoDate() {
        return getDataStringInyyyyMMdd(todoDate);
    }

    @JsonProperty("startDate")
    public String getStartDate() {
        return getDataStringInyyyyMMdd(startDate);
    }

    @JsonProperty("endDate")
    public String getEndDate() {
        return getDataStringInyyyyMMdd(endDate);
    }

    @JsonProperty("todoDate")
    public void setTodoDate(Date todoDate) {
        this.todoDate = todoDate;
    }

    @JsonProperty("startDate")
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @JsonProperty("endDate")
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

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
