package com.tcp.iamlazy.setting.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter @Setter
@ToString
public class Setting {
    @NotNull(message = "userIdx cannot be null.")
    private int userIdx;
    private int statsYN;
    private int achieveYN;
    private int failYN;
}
