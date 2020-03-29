package com.example.tangboyang1.controller;

import com.example.tangboyang1.dto.ResultDTO;
import com.example.tangboyang1.pojo.Address;
import com.example.tangboyang1.request.AddressRequest.AddAddressRequest;
import com.example.tangboyang1.request.AddressRequest.UpdateAddressRequest;
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
    public List<Address> findAllAddress(Integer pageNumber, Integer pageSize){
        return addressService.findAllAddress(pageNumber,pageSize);
    }


    @PostMapping(value = "add/Address/by/json")
    public Integer addAddressByJson(@RequestBody AddAddressRequest addAddressRequest){
        return addressService.addAddress(addAddressRequest);
    }

    @DeleteMapping(value = "delete/address/by/id")
    public Integer deleteAddress(Integer id) {
        return addressService.deleteAddress(id);
    }


@PostMapping(value = "update/address/by/id")
    public Integer updateAddressByid(@RequestBody UpdateAddressRequest updateAddressRequest) {
        return addressService.updateAddressByid(updateAddressRequest);
    }

    @DeleteMapping(value = "delete/address/by/userid")
    public Integer deleteAddressByUserid( ) {
        return addressService.deleteAddressByUserid();
    }

    @GetMapping(value = "find/address/by/userid")
    public List<Address> findAddressByUserid( ) {
        return addressService.findAddressByUserid();
    }

    @GetMapping(value = "find/startaddress/by/userid")
    public Address findStartAddressByUserid() {
        return addressService.findStartAddressByUserid();
    }

    @GetMapping(value = "update/startaddress/by/addressid")
    public ResultDTO UpdateStartAddressByUserid(Integer id) {
        return addressService.UpdateStartAddressByUserid(id);
    }
}
