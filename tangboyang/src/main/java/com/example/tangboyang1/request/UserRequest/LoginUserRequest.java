package com.example.tangboyang1.request.UserRequest;

import lombok.Data;

import javax.servlet.http.HttpServletRequest;

@Data
public class LoginUserRequest  {

    private static final long serialVersionUID = 8395452222332036201L;
    private  HttpServletRequest request;
//    private String username;
//    private String password;

    private String code;

//    public static long getSerialVersionUID() {
//        return serialVersionUID;
//    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
