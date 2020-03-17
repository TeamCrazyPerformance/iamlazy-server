package com.tcp.iamlazy.setting;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter @Setter
@ToString
public class Setting {
    @NotNull(message = "All field cannot be null.")
    private int userIdx;
    private int statsYN;
    private int achieveYN;
    private int failYN;
}
