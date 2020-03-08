package com.example.tangboyang1.service;

import com.example.tangboyang1.request.StoreRequest.AddStoreRequest;
import org.springframework.stereotype.Service;

@Service
public interface StoreService {

    Integer RegistStore(AddStoreRequest addStoreRequest);
}
