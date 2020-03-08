package com.example.tangboyang1.service.impl;

import com.example.tangboyang1.dao.ProductDao;
import com.example.tangboyang1.dao.ShoppingCartDao;
import com.example.tangboyang1.pojo.Products;
import com.example.tangboyang1.pojo.ShoppingCart;
import com.example.tangboyang1.request.ShoppingRequest.AddShoppingcartRequest;
import com.example.tangboyang1.service.ShoppingCartService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Autowired
    private ShoppingCartDao shoppingCartDao;
    @Autowired
    private ProductDao productDao;
    @Override
    public Integer addShoppingCart(AddShoppingcartRequest addShoppingcartRequest,Integer id) {
        ShoppingCart shoppingCart=new ShoppingCart();
        shoppingCart.setUserId(id);
        shoppingCart.setNum(addShoppingcartRequest.getNum());
        shoppingCart.setProductsId(addShoppingcartRequest.getProductsId());
        Products productsid = productDao.findBookById(addShoppingcartRequest.getProductsId());
        shoppingCart.setMuney(productsid.getPrice()*addShoppingcartRequest.getNum());
        shoppingCart.setCreattime(new Date());
        return shoppingCartDao.addShoppingCart(shoppingCart);
    }

    @Override
    public Integer deleteShoppingCart(Integer id) {
        return shoppingCartDao.deleteShoppingCart(id);
    }

    @Override
    public Integer updateShoppingCart(ShoppingCart user) {
        return shoppingCartDao.updateShoppingCart(user);
    }

    @Override
    public List<ShoppingCart> findAllShoppingCart(Integer pageNumber, Integer pageSize) {
        PageHelper.startPage(pageNumber,pageSize);
        List<ShoppingCart> all=shoppingCartDao.findAllShoppingCart();
        PageInfo<ShoppingCart> pageInfo=new PageInfo<>(all);
        return pageInfo.getList();

    }

    @Override
    public ShoppingCart findshopByid(Integer id) {
        return shoppingCartDao.findshopByid(id);
    }

    @Override
    public List<ShoppingCart> findshopByUserid(Integer userid) {
        return shoppingCartDao.findshopByUserid(userid);
    }

    @Override
    public Integer deleteShopByUserid(Integer userid) {
        return shoppingCartDao.deleteShopByUserid(userid);
    }

    @Override
    public Integer updateReducePNum(int num, Integer id) {
        return shoppingCartDao.updateReducePNum(num,id);
    }

    @Override
    public Integer updatAddPNum(int num, Integer id) {
        return shoppingCartDao.updatAddPNum(num, id);
    }
}
