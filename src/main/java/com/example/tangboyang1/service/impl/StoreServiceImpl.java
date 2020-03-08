package com.example.tangboyang1.service.impl;

import com.example.tangboyang1.dao.StoreDao;
import com.example.tangboyang1.pojo.Store;
import com.example.tangboyang1.request.StoreRequest.AddStoreRequest;
import com.example.tangboyang1.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class StoreServiceImpl implements StoreService {
    @Autowired
    private StoreDao storeDao;
    @Override
    public Integer RegistStore(AddStoreRequest asr) {
        Store store = new Store();
        String s = UUID.randomUUID().toString();
        store.setStoreCode(s);
        System.out.println(s);
        store.setStoreName(asr.getStoreName());
        store.setStorePhone(asr.getStorePhone());
        store.setStoreType(asr.getStoreType());
        store.setUserId(1);
        store.setCreatTime(new Date());
        return storeDao.addStore(store);
    }
}
