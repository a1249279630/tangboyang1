package com.example.tangboyang1.request.StoreRequest;

import lombok.Data;

import javax.servlet.http.HttpServletRequest;
@Data
public class AddStoreRequest {

//    private HttpServletRequest request;

    private String storeName;

    private String storePhone;

    private String storeType;


}
