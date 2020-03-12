package com.example.tangboyang1.service;

import com.example.tangboyang1.pojo.Address;
import com.example.tangboyang1.request.AddressRequest.AddAddressRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AddressService {
    Integer addAddress(AddAddressRequest addAddressRequest);


    Integer deleteAddress(Integer id);

//    Integer updateAddress(Address address);

    List<Address> findAllAddress(Integer pageNumber, Integer pageSize);

    Integer updateAddressByid(AddAddressRequest addAddressRequest);

    Integer deleteAddressByUserid(Integer userid);

    List<Address> findAddressByUserid(Integer userid);
}
