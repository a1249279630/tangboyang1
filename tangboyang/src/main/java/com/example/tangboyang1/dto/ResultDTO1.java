package com.example.tangboyang1.dto;


import com.example.tangboyang1.error.IErrorCode;
import com.example.tangboyang1.pojo.User;
import lombok.Data;

/**
 * Created by codedrinker on 2018/11/25.
 */
@Data
public class ResultDTO1 {
    private Integer status;

    private String message;
    private User user;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }



    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public static ResultDTO1 ok(User user) {
        ResultDTO1 resultDTO = new ResultDTO1();
        resultDTO.setStatus(200);
        resultDTO.setUser(user);
        resultDTO.setMessage("请求成功");
        return resultDTO;
    }

    public static ResultDTO1 fail(String message) {
        ResultDTO1 resultDTO = new ResultDTO1();
        resultDTO.setStatus(400);
        resultDTO.setMessage(message);
        return resultDTO;
    }

    public static ResultDTO1 fail(IErrorCode errorCode) {
        ResultDTO1 resultDTO = new ResultDTO1();
        resultDTO.setStatus(errorCode.getCode());
        resultDTO.setMessage(errorCode.getMessage());
        return resultDTO;
    }
}
