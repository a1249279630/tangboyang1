package com.example.tangboyang1.response;

import lombok.Data;

import java.util.Date;

@Data
public class LoginUserResponse {
    private Integer id;

    private String username;

    private String password;

    private String gender;

    private String email;

    private String telephone;

    private String introduce;

    private String activecode;

    private Integer state;

    private String role;

    private static final long serialVersionUID = 7880583397593331665L;

    private String session_key;


}
