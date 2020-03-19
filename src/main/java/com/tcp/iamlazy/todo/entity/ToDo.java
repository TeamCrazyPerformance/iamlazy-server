package com.tcp.iamlazy.todo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter @Setter
@ToString
public class ToDo {
    @NotNull(message = "userIdx cannot be null.")
    private int userIdx;
    @NotNull(message = "todoIdx cannot be null.")
    private int todoIdx;
    private String todoTitle;
    private String todoContent;
    private Date todoDate;
    private int repeatableYN;
    private int repeatUnit;
    private Date startDate;
    private Date endDate;
    private String weekDay;
    private int monthDay;
    private int finish;



}
