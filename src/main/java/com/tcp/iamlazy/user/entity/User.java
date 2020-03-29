package com.tcp.iamlazy.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tcp.iamlazy.user.model.KakaoPrincipal;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class User {
    @NotNull(message = "userIdx cannot be null.")
    private int userIdx;
    private String userKakaoID;
    private String userName;
    private String userImage;

    @JsonIgnore
    public static User retrieveUserFromKakao(KakaoPrincipal kakaoAccount) {
        User user = new User();
        user.setUserKakaoID(kakaoAccount.getId());
        user.setUserImage(kakaoAccount.getUserImageLink());
        user.setUserName(kakaoAccount.getUserName());

        return user;
    }

}
