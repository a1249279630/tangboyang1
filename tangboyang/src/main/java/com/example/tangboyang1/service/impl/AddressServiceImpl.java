package com.example.tangboyang1.service.impl;

import com.example.tangboyang1.dao.AddressDao;
import com.example.tangboyang1.dto.ResultDTO;
import com.example.tangboyang1.pojo.Address;
import com.example.tangboyang1.pojo.User;
import com.example.tangboyang1.request.AddressRequest.AddAddressRequest;
import com.example.tangboyang1.request.AddressRequest.UpdateAddressRequest;
import com.example.tangboyang1.service.AddressService;
import com.example.tangboyang1.session.SessionUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressDao addressDao;
    @Override
    public Integer addAddress(AddAddressRequest adr) {
        User user = SessionUtil.getUser();
        Address address=new Address();
        address.setReceiveaddress(adr.getReceiveaddress());
        address.setReceivename(adr.getReceivename());
        address.setReceivephone(adr.getReceivephone());
        address.setState(0);
        address.setUserid(user.getId());
        return addressDao.addAddress(address);
    }

    @Override
    public Integer deleteAddress(Integer id) {
        return addressDao.deleteAddress(id);
    }

//    @Override
////    public Integer updateAddress(Address address) {
////        return addressDao.updateAddress(address);
////    }

    @Override
    public List<Address> findAllAddress(Integer pageNumber, Integer pageSize) {
        PageHelper.startPage(pageNumber,pageSize);
        List<Address> all=addressDao.findAllAddress();
        PageInfo<Address> pageInfo=new PageInfo<>(all);
        return pageInfo.getList();
    }

    @Override
    public Integer updateAddressByid(UpdateAddressRequest udr) {
        Address address = addressDao.findAddressByid(udr.getId());
        address.setReceiveaddress(udr.getReceiveaddress());
        address.setReceivename(udr.getReceivename());
        address.setReceivephone(udr.getReceivephone());
        return addressDao.updateAddressByid(address);
    }

    @Override
    public Integer deleteAddressByUserid() {
        User user = SessionUtil.getUser();
        return addressDao.deleteAddressByUserid(user.getId());
    }

    @Override
    public List<Address> findAddressByUserid() {
        User user = SessionUtil.getUser();
        return addressDao.findAddressByUserid(user.getId());
    }

    @Override
    public Address findStartAddressByUserid() {
        List<Address> addressByUserid = findAddressByUserid();
        if (!CollectionUtils.isEmpty(addressByUserid)){
            if(addressByUserid.size()==1){
                Address address = addressByUserid.get(0);
                address.setState(1);
                addressDao.updateAddressByid(address);
                return address;
            }
            for (Address a:addressByUserid) {
                if(a.getState().equals(1)){
                    return a;
                }
            }
        }
        return null;
    }

    @Override
    public ResultDTO UpdateStartAddressByUserid(Integer id) {
        List<Address> addressByUserid = findAddressByUserid();
        Address address = addressDao.findAddressByid(id);
        address.setState(1);
        addressDao.updateAddressByid(address);
        for(Address a:addressByUserid){
            if(a.getId()!=address.getId()){
                a.setState(0);
                addressDao.updateAddressByid(a);
            }
        }
        return ResultDTO.ok("ok");
    }
}
