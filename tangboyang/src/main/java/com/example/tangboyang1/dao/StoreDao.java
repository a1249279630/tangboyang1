package com.example.tangboyang1.dao;

import com.example.tangboyang1.mapper.StoreMapper;
import com.example.tangboyang1.pojo.Store;
import com.example.tangboyang1.pojo.StoreExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StoreDao {
    @Autowired
    private StoreMapper storeMapper;

    public List<Store> findStoreByUserid(Integer id) {
        StoreExample storeExample=new StoreExample();
        storeExample.createCriteria().andUserIdEqualTo(id);
        return storeMapper.selectByExample(storeExample);
    }


    public Integer addStore(Store store){
        return storeMapper.insert(store);
    }


}
