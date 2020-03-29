package com.example.tangboyang1.dao;

import com.example.tangboyang1.mapper.AddressMapper;
import com.example.tangboyang1.pojo.Address;
import com.example.tangboyang1.pojo.AddressExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class AddressDao {
    @Autowired
    private AddressMapper addressMapper;

    public List<Address> findAllAddress(){
        AddressExample addressExample=new AddressExample();
        return addressMapper.selectByExample(addressExample);
    }

    public Integer addAddress(Address address){
        return addressMapper.insert(address);
    }

    public Integer deleteAddress(Integer id){
        return addressMapper.deleteByPrimaryKey(id);
    }
//    public Integer updateAddress(Address address){
//        return addressMapper.updateByPrimaryKey(address);
//    }

    public Integer updateAddressByid(Address address){
        return addressMapper.updateByPrimaryKey(address);
    }

    public Integer deleteAddressByUserid(Integer userid){
        AddressExample addressExample=new AddressExample();
        addressExample.createCriteria().andUseridEqualTo(userid);
        return addressMapper.deleteByExample(addressExample);
    }

    public List<Address> findAddressByUserid(Integer userid){
        AddressExample addressExample=new AddressExample();
        addressExample.createCriteria().andUseridEqualTo(userid);
        return addressMapper.selectByExample(addressExample);
    }

    public Address findAddressByid(Integer id) {
        return addressMapper.selectByPrimaryKey(id);
    }
}
