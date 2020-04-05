package com.example.tangboyang1.service.impl;

import com.example.tangboyang1.dao.ProductDao;
import com.example.tangboyang1.dao.ShoppingCartDao;
import com.example.tangboyang1.error.CommonErrorCode;
import com.example.tangboyang1.error.ErrorCodeException;
import com.example.tangboyang1.pojo.Products;
import com.example.tangboyang1.pojo.ShoppingCart;
import com.example.tangboyang1.pojo.User;
import com.example.tangboyang1.request.ShoppingRequest.AddShoppingcartRequest;
import com.example.tangboyang1.service.ShoppingCartService;
import com.example.tangboyang1.session.SessionUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Autowired
    private ShoppingCartDao shoppingCartDao;
    @Autowired
    private ProductDao productDao;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Override
    public Integer addShoppingCart(AddShoppingcartRequest addShoppingcartRequest) {
        try {
            User user = SessionUtil.getUser();
        System.out.println(addShoppingcartRequest.getProductsId());
            ShoppingCart shoppingCart1 = shoppingCartDao.findShopcartByUseridAndProductid(user.getId(), addShoppingcartRequest.getProductsId());
            Products productsid = productDao.findBookById(addShoppingcartRequest.getProductsId());
            if(StringUtil.isEmpty(productsid.toString())){
                throw new ErrorCodeException(CommonErrorCode.Product_id_Error);
            }
            try{
                ShoppingCart shoppingCart = shoppingCart1;
                if(addShoppingcartRequest.getNum()+shoppingCart.getNum()>productsid.getPnum()){
                    return  -2;
                }
                Double aDouble = transDouble(shoppingCart.getProductPrice() * addShoppingcartRequest.getNum());
                shoppingCart.setMuney(shoppingCart.getMuney()+ aDouble);
                shoppingCart.setNum(shoppingCart.getNum()+addShoppingcartRequest.getNum());
                return shoppingCartDao.updateShoppingCart(shoppingCart);
            }catch (Exception e){}
            ShoppingCart shoppingCart=new ShoppingCart();
            shoppingCart.setUserId(user.getId());
            shoppingCart.setNum(addShoppingcartRequest.getNum());
            shoppingCart.setProductsId(addShoppingcartRequest.getProductsId());

            if(addShoppingcartRequest.getNum()>productsid.getPnum()){
                return  -2;
            }
            shoppingCart.setMuney(productsid.getCurrentprice()*addShoppingcartRequest.getNum());
            shoppingCart.setProductPrice(productsid.getCurrentprice());
            shoppingCart.setProductImage(productsid.getImgurl());
            shoppingCart.setProductDes(productsid.getDescription());
            shoppingCart.setCreattime(new Date());
            return shoppingCartDao.addShoppingCart(shoppingCart);
        }catch (ErrorCodeException e){
            return -3;
        }catch (Exception e){
            return -1;
        }
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
    public List<ShoppingCart> findshopByUserid() {
        User user = SessionUtil.getUser();
        System.out.println(user.getId());
        return shoppingCartDao.findshopByUserid(user.getId());
    }

    @Override
    public Integer deleteShopByUserid() {
        User user = SessionUtil.getUser();

        return shoppingCartDao.deleteShopByUserid(user.getId());
    }

    @Override
    public Integer updateReducePNum(int num, Integer id) {
        return shoppingCartDao.updateReducePNum(num,id);
    }

    @Override
    public Integer updatAddPNum(int num, Integer id) {
        return shoppingCartDao.updatAddPNum(num, id);
    }

    public Double transDouble (Double d){
        DecimalFormat num = new DecimalFormat("#.00");
        String random=num.format(d);
        double n=Double.parseDouble(random);
        return n;
    }
}
