package com.tcp.iamlazy.user.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter @Setter
@ToString
public class User {
    @NotNull(message = "userIdx cannot be null.")
    private int userIdx;
    private String userKakaoID;
    private String userName;
    private String userImage;

}
