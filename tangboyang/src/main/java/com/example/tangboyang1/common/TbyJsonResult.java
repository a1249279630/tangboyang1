package com.example.tangboyang1.common;

public class TbyJsonResult<T> {
    // 响应业务状态
    private Integer status;

    // 响应消息
    private String msg;

    // 响应中的数据
    private Object data;

    private String ok; // 不使用


    public static TbyJsonResult build(Integer status, String msg, Object data) {
        return new TbyJsonResult(status, msg, data);
    }

    public static TbyJsonResult ok(Object data) {
        return new TbyJsonResult(data);
    }

    public static TbyJsonResult ok() {
        return new TbyJsonResult(null);
    }

    public static TbyJsonResult errorMsg(String msg) {
        return new TbyJsonResult(500, msg, null);
    }

    public static TbyJsonResult errorMap(Object data) {
        return new TbyJsonResult(501, "error", data);
    }

    public static TbyJsonResult errorTokenMsg(String msg) {
        return new TbyJsonResult(502, msg, null);
    }

    public static TbyJsonResult errorException(String msg) {
        return new TbyJsonResult(555, msg, null);
    }

    public TbyJsonResult() {

    }

    public TbyJsonResult(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public TbyJsonResult(Object data) {
        this.status = 200;
        this.msg = "OK";
        this.data = data;
    }

    public Boolean isOK() {
        return this.status == 200;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getOk() {
        return ok;
    }

    public void setOk(String ok) {
        this.ok = ok;
    }
}
