package com.example.tangboyang1.response;

import com.example.tangboyang1.pojo.Store;
import com.example.tangboyang1.pojo.User;
import lombok.Data;

@Data
public class FindAllUserResponse {
    private User user;
    private Store store;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }
}
