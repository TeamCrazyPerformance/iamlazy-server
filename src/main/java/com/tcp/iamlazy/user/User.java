package com.tcp.iamlazy.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter @Setter
@ToString
public class User {
    @NotNull(message = "All field cannot be null.")
    private int userIdx;
    private String userKakaoID;
    private String userName;
    private String userImage;

}
