package com.example.tangboyang1.request.UserRequest;

public class UpdateUserPasswardRequest {
    private String username;

    private String password;

    private String Copypassword;

    private String activecode;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCopypassword() {
        return Copypassword;
    }



    public String getActivecode() {
        return activecode;
    }

    public void setCopypassword(String copypassword) {
        Copypassword = copypassword;
    }

    public void setActivecode(String activecode) {
        this.activecode = activecode;
    }
}
