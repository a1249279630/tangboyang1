package com.example.tangboyang1.pojo;

public class Address {
    private Integer id;

    private String receiveaddress;

    private String receivename;

    private String receivephone;

    private Integer userid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReceiveaddress() {
        return receiveaddress;
    }

    public void setReceiveaddress(String receiveaddress) {
        this.receiveaddress = receiveaddress == null ? null : receiveaddress.trim();
    }

    public String getReceivename() {
        return receivename;
    }

    public void setReceivename(String receivename) {
        this.receivename = receivename == null ? null : receivename.trim();
    }

    public String getReceivephone() {
        return receivephone;
    }

    public void setReceivephone(String receivephone) {
        this.receivephone = receivephone == null ? null : receivephone.trim();
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }
}