package com.example.tangboyang1.controller;//package com.example.tangboyang.controller;

import com.example.tangboyang1.pojo.ShoppingCart;
import com.example.tangboyang1.request.ShoppingRequest.AddShoppingcartRequest;
import com.example.tangboyang1.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;
    @PostMapping(value = "find/all/ShoppingCart")
    public List<ShoppingCart> findAllUser(@RequestBody Integer pageNumber, Integer pageSize){

        return shoppingCartService.findAllShoppingCart(pageNumber,pageSize);
    }

    /**
     * 通过form表单提交
     * @param shoppingCart
     * @return
     */
  /*  @PostMapping(value = "add/shoppingCartServic")
    public Integer addShoppingCart(@RequestBody ShoppingCart shoppingCart){
    return shoppingCartService.addShoppingCart(shoppingCart);
}*/
    /**
     * 通过json提交
     * @param
     * @return
     */
    @PostMapping(value = "add/shoppingCart/by/json")
    public Integer addShoppingCartByJson(@RequestBody AddShoppingcartRequest addShoppingcartRequest,Integer id){
        return shoppingCartService.addShoppingCart(addShoppingcartRequest,id);
    }

    @DeleteMapping(value = "delete/shoppingCart/by/id")
    public Integer deleteShoppingCart(@RequestBody Integer id) {
        return shoppingCartService.deleteShoppingCart(id);
    }

    /*@PutMapping(value = "update/user/by/shoppingCart")
    public Integer updateShoppingCart(@RequestBody ShoppingCart shoppingCart) {
        return shoppingCartService.updateShoppingCart(shoppingCart);
    }*/

    @GetMapping(value = "find/shopping/by/id")
    public ShoppingCart findshopByid(Integer id) {
        return shoppingCartService.findshopByid(id);
    }

    @GetMapping(value = "find/shopping/by/userid")
    public List<ShoppingCart> findshopByUserid(Integer userid) {
        return shoppingCartService.findshopByUserid(userid);
    }

    @DeleteMapping(value = "delete/shopping/by/userid")
    public Integer deleteShopByUserid(Integer userid) {
        return shoppingCartService.deleteShopByUserid(userid);
    }

    @PostMapping(value = "update/shoppingReducenum/by/id")
    public Integer updateReducePNum(int num, Integer id) {
        return shoppingCartService.updateReducePNum(num,id);
    }

    @PostMapping(value = "update/shoppingAddnum/by/id")
    public Integer updatAddPNum(int num, Integer id) {
        return shoppingCartService.updatAddPNum(num, id);
    }
}
