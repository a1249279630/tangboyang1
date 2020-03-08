package com.example.tangboyang1.controller;/*
package com.example.tangboyang.controller;

import com.example.tangboyang.pojo.Address;
import com.example.tangboyang.request.AddAddressRequest;
import com.example.tangboyang.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AddressController {
    @Autowired
private AddressService addressService;


    @PostMapping(value = "find/allAddress")
    public List<Address> findAllAddress(@RequestBody Integer pageNumber, Integer pageSize){
        return addressService.findAllAddress(pageNumber,pageSize);
    }



    @PostMapping(value = "add/Address/by/json")
    public Integer addAddressByJson(@RequestBody AddAddressRequest addAddressRequest, int id){
        return addressService.addAddress(addAddressRequest,id);
    }
//
//
    @DeleteMapping(value = "delete/address/by/id")
    public Integer deleteAddress(@RequestBody Integer id) {
        return addressService.deleteAddress(id);
    }

   */
/* @PostMapping(value = "update/address/by/id")
    public Integer updateAddressByid(@RequestBody AddAddressRequest addAddressRequest) {
        return addressService.updateAddressByid(addAddressRequest);
    }

    @DeleteMapping(value = "delete/address/by/userid")
    public Integer deleteAddressByUserid(@RequestBody Integer userid) {
        return addressService.deleteAddressByUserid(userid);
    }

    @GetMapping(value = "find/address/by/userid")
    public List<Address> findAddressByUserid(@RequestBody Integer userid) {
        return addressService.findAddressByUserid(userid);
    }*//*

}





*/
