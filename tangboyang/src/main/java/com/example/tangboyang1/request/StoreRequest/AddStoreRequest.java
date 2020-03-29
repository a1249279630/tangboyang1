package com.example.tangboyang1.request.StoreRequest;

import lombok.Data;

@Data
public class AddStoreRequest {

//    private HttpServletRequest request;

    private String storeName;

    private String storePhone;

    private String storeType;

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStorePhone() {
        return storePhone;
    }

    public void setStorePhone(String storePhone) {
        this.storePhone = storePhone;
    }

    public String getStoreType() {
        return storeType;
    }

    public void setStoreType(String storeType) {
        this.storeType = storeType;
    }
}
