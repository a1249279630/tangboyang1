package com.example.tangboyang1.dto;


import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * Created by codedrinker on 2018/11/24.
 */
@Data
public class SessionDTO {
    private String openid;

    @JSONField(name = "session_key")
    private String sessionKey;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }
}