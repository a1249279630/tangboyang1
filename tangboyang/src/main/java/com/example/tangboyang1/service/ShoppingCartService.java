package com.example.tangboyang1.service;

import com.example.tangboyang1.pojo.ShoppingCart;
import com.example.tangboyang1.request.ShoppingRequest.AddShoppingcartRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ShoppingCartService {
    Integer addShoppingCart(AddShoppingcartRequest addShoppingcartRequest);
    Integer deleteShoppingCart(Integer id);

    Integer updateShoppingCart(ShoppingCart user);

    List<ShoppingCart> findAllShoppingCart(Integer pageNumber, Integer pageSize);

    ShoppingCart findshopByid(Integer id);

    List<ShoppingCart> findshopByUserid();

    Integer deleteShopByUserid();

    /*通过id减少购物车产品的数量*/
    Integer updateReducePNum(int num, Integer id);

    /*通过id增加购物车产品的数量*/
    Integer updatAddPNum(int num, Integer id) ;

}
