package com.example.tangboyang1.service.impl;

import com.example.tangboyang1.dao.StoreDao;
import com.example.tangboyang1.pojo.Store;
import com.example.tangboyang1.pojo.User;
import com.example.tangboyang1.request.StoreRequest.AddStoreRequest;
import com.example.tangboyang1.service.StoreService;
import com.example.tangboyang1.session.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

@Service
public class StoreServiceImpl implements StoreService {
    @Autowired
    private StoreDao storeDao;
    @Override
    public Integer RegistStore(AddStoreRequest asr) {
        Store store = new Store();
        store.setStoreCode(randomSafetyCode());
        store.setStoreName(asr.getStoreName());
        store.setStorePhone(asr.getStorePhone());
        store.setStoreType(asr.getStoreType());
        User user = SessionUtil.getUser();
        store.setUserId(user.getId());
        store.setCreatTime(new Date());
        return storeDao.addStore(store);
    }



    public String randomSafetyCode(){
        char[] str = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
        int count = 0;
        int i;
        int maxNum = 62;
        StringBuffer stringBuffer = new StringBuffer();
        Random random = new Random();
        while(count < 8){
            i = Math.abs(random.nextInt(maxNum));
            if (i >= 0 && i < str.length) {
                stringBuffer.append(str[i]);
                count ++;
            }
        }
        return stringBuffer.toString();
    }
}
