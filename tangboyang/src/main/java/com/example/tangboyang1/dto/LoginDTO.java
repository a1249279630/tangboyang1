package com.example.tangboyang1.dto;

import lombok.Data;

/**
 * Created by codedrinker on 2018/11/24.
 */
@Data
public class LoginDTO {
//    // 用户信息原始数据
//    private String rawData;
//
//    // 用于验证用户信息是否被篡改过
//    private String signature;
//
//    // 用户获取 session_key 的 code
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
