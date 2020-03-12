package com.example.tangboyang1.controller;//package com.example.tangboyang.controller;

import com.example.tangboyang1.dto.ResultDTO;
import com.example.tangboyang1.error.CommonErrorCode;
import com.example.tangboyang1.pojo.ShoppingCart;
import com.example.tangboyang1.request.ShoppingRequest.AddShoppingcartRequest;
import com.example.tangboyang1.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ShoppingCartController")
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
    @PostMapping(value = "add/shoppingCart")
    public ResultDTO addShoppingCartBy( AddShoppingcartRequest addShoppingcartRequest){
        Integer integer = shoppingCartService.addShoppingCart(addShoppingcartRequest);
        if(integer==1){
            return ResultDTO.ok("添加成功");
        }else {
            return ResultDTO.fail(CommonErrorCode.INVALID_PARAMS);
        }
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

    @GetMapping(value = "find/Allshopping/by/userid")
    public List<ShoppingCart> findshopByUserid() {
        return shoppingCartService.findshopByUserid();
    }

    @DeleteMapping(value = "delete/Allshopping/by/userid")
    public Integer deleteShopByUserid() {
        return shoppingCartService.deleteShopByUserid();
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
