package com.tcp.iamlazy.statistics;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter @Setter
@ToString
public class Statistics {
    @NotNull(message = "All field cannot be null.")
    private int userIdx;
    private int statsIdx;
    private Date regDate;
    private int endValue;
}
