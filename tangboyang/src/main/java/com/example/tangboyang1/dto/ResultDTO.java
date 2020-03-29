package com.example.tangboyang1.dto;


import com.example.tangboyang1.error.IErrorCode;
import lombok.Data;

/**
 * Created by codedrinker on 2018/11/25.
 */
@Data
public class ResultDTO {
    private Integer status;
    private String token;
    private String message;

    public static ResultDTO ok(String data) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setStatus(200);
        resultDTO.setToken(data);
        resultDTO.setMessage("请求成功");
        return resultDTO;
    }

    public static ResultDTO fail(String message) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setStatus(400);
        resultDTO.setMessage(message);
        return resultDTO;
    }

    public static ResultDTO fail(IErrorCode errorCode) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setStatus(errorCode.getCode());
        resultDTO.setMessage(errorCode.getMessage());
        return resultDTO;
    }
}
