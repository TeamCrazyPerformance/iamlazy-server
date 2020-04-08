package com.tcp.iamlazy.user.entity;

import com.tcp.iamlazy.configuration.security.oauth2.AuthProvider;
import javax.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class User {
    @NotNull(message = "userIdx cannot be null.")
    private long userIdx;
    private String userKakaoID;
    private String userName;
    private String userImage;

    private String password;
    private AuthProvider provider;
    private String providerId;
    private String userEmail;

    public long getKakaoIdAsLong() {
        return Long.parseLong(this.getUserKakaoID());
    }

    public void setProviderId(String id) {
        this.providerId = id;
    }
}
