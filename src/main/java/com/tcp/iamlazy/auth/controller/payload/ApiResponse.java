package com.tcp.iamlazy.auth.controller.payload;

import lombok.Getter;
import lombok.Setter;

/**
 * 응답의 body를 제공하기 위하여 생성합니다.
 */
@Getter
@Setter
public class ApiResponse {
    private boolean success;
    private String message;

    public ApiResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

}
