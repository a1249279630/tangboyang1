package com.example.tangboyang1.service;

import com.example.tangboyang1.dto.ResultDTO;
import com.example.tangboyang1.pojo.Address;
import com.example.tangboyang1.request.AddressRequest.AddAddressRequest;
import com.example.tangboyang1.request.AddressRequest.UpdateAddressRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AddressService {
    Integer addAddress(AddAddressRequest addAddressRequest);


    Integer deleteAddress(Integer id);

//    Integer updateAddress(Address address);

    List<Address> findAllAddress(Integer pageNumber, Integer pageSize);

    Integer updateAddressByid(UpdateAddressRequest udr);

    Integer deleteAddressByUserid();

    List<Address> findAddressByUserid();

    Address findStartAddressByUserid();

    ResultDTO UpdateStartAddressByUserid(Integer id);
}
