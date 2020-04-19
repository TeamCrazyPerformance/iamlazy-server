package com.tcp.iamlazy.review.entity;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter @Setter
@ToString
public class Review {
    @NotNull(message = "User number cannot be null.")
    private int userIdx;
    @NotNull(message = "todoDate cannot be null.")
    private String toDoDate;
    private String reviewContent;
    private int emoticon;

}
