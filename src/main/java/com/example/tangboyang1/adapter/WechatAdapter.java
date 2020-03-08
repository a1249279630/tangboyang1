package com.example.tangboyang1.adapter;

import com.example.tangboyang1.dto.SessionDTO;

import com.example.tangboyang1.error.CommonErrorCode;
import com.example.tangboyang1.error.ErrorCodeException;
import com.example.tangboyang1.request.HttpRequest;
import org.json.JSONObject;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class WechatAdapter {

    private  static String appid="wxf443534117ab8eae";

    private  static String secret="9b5cd3d2ad0588509d4628b2e285b945";



    public SessionDTO jscode2session(String code) {
        String params = "appid=" + appid + "&secret=" + secret + "&js_code=" + code + "&grant_type=authorization_code";
        // 发送请求
        String sr = HttpRequest.sendGet("https://api.weixin.qq.com/sns/jscode2session", params);
        Map map = new HashMap();
        try {
            // 解析相应内容（转换成json对象）
            JSONObject json = new JSONObject(sr);
            // 获取会话密钥（session_key）

            String session_key = json.get("session_key").toString();
            // 用户的唯一标识（openid）
            String openid = (String) json.get("openid").toString();

            map.put( "session_key",session_key);
            map.put( "openId",openid );
            SessionDTO sessionDTO=new SessionDTO();
            sessionDTO.setOpenid(openid);
            sessionDTO.setSessionKey(session_key);
            return sessionDTO;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ErrorCodeException(CommonErrorCode.OBTAIN_OPENID_ERROR);
        }
    }


}
