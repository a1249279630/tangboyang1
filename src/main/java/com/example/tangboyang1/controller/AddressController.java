package com.example.tangboyang1.controller;

import com.example.tangboyang1.pojo;
import com.example.tangboyang1.request.AddressRequest.AddAddressRequest;
import com.example.tangboyang1.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("AddressController")
public class AddressController {
    @Autowired
    private AddressService addressService;


    @PostMapping(value = "find/allAddress")
    public List<Address> findAllAddress(@RequestBody Integer pageNumber, Integer pageSize){
        return addressService.findAllAddress(pageNumber,pageSize);
    }



    @PostMapping(value = "add/Address/by/json")
    public Integer addAddressByJson(@RequestBody AddAddressRequest addAddressRequest){
        return addressService.addAddress(addAddressRequest);
    }

    @DeleteMapping(value = "delete/address/by/id")
    public Integer deleteAddress(@RequestBody Integer id) {
        return addressService.deleteAddress(id);
    }


@PostMapping(value = "update/address/by/id")
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
    }

}
