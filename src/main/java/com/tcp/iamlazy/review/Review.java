package com.tcp.iamlazy.review;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter @Setter
@ToString
public class Review {
    @NotNull(message = "User number cannot be null.")
    private int userIdx;
    private String purposeDate;
    private String reviewContent;
    private int emoticon;
}
