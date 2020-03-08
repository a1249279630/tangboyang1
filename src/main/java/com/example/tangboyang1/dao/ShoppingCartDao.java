package com.example.tangboyang1.dao;

import com.example.tangboyang1.mapper.ProductsMapper;
import com.example.tangboyang1.mapper.ShoppingCartMapper;
import com.example.tangboyang1.pojo.Products;
import com.example.tangboyang1.pojo.ShoppingCart;
import com.example.tangboyang1.pojo.ShoppingCartExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class ShoppingCartDao {
    @Autowired
    private ShoppingCartMapper shoppingCartMapper;
    @Autowired
    private ProductsMapper productsMapper;

    public List<ShoppingCart> findAllShoppingCart(){
        ShoppingCartExample shoppingCartExample=new ShoppingCartExample();
        return shoppingCartMapper.selectByExample(shoppingCartExample);
    }

    public Integer addShoppingCart(ShoppingCart shoppingCart){
        return shoppingCartMapper.insert(shoppingCart);
    }

    public Integer deleteShoppingCart(int id){
        return shoppingCartMapper.deleteByPrimaryKey(id);
    }
    public Integer updateShoppingCart(ShoppingCart shoppingCart){
        return shoppingCartMapper.updateByPrimaryKey(shoppingCart);
    }

    public ShoppingCart findshopByid(Integer id){
    return shoppingCartMapper.selectByPrimaryKey(id);
}

    public List<ShoppingCart> findshopByUserid(Integer userid){
        ShoppingCartExample shoppingCartExample=new ShoppingCartExample();
        shoppingCartExample.createCriteria().andUserIdEqualTo(userid);
        return shoppingCartMapper.selectByExample(shoppingCartExample);
    }

    public Integer deleteShopByUserid(Integer userid){
        ShoppingCartExample shoppingCartExample=new ShoppingCartExample();
        shoppingCartExample.createCriteria().andUserIdEqualTo(userid);
        return shoppingCartMapper.deleteByExample(shoppingCartExample);
    }

    /*通过id减少购物车产品的数量*/
    public Integer updateReducePNum(int num,Integer id) {
        ShoppingCart shoppingCart = shoppingCartMapper.selectByPrimaryKey(id);

        if(shoppingCart.getNum()>num&&shoppingCart.getNum()>0){

            shoppingCart.setNum(shoppingCart.getNum()-num);

            Products products = productsMapper.selectByPrimaryKey(shoppingCart.getProductsId());

            shoppingCart.setMuney(shoppingCart.getMuney()-products.getPrice()*num);
            shoppingCart.setCreattime(new Date());
            products.setPnum(products.getPnum()+num);
            if(shoppingCart.getNum()==0){
                return shoppingCartMapper.deleteByPrimaryKey(shoppingCart.getId());
            }
            productsMapper.updateByPrimaryKey(products);
        }else{
            new Exception("数量不对");
        }

        return shoppingCartMapper.updateByPrimaryKey(shoppingCart);
    }

    /*通过id增加购物车产品的数量*/
    public Integer updatAddPNum(int num,Integer id) {
        ShoppingCart shoppingCart = shoppingCartMapper.selectByPrimaryKey(id);
        Products products = productsMapper.selectByPrimaryKey(shoppingCart.getProductsId());
        if (products.getPnum() > num && products.getPnum() > 0) {
            products.setPnum(products.getPnum() - num);
            shoppingCart.setNum(shoppingCart.getNum() + num);
            shoppingCart.setMuney(shoppingCart.getMuney() + num * products.getPrice());
            shoppingCart.setCreattime(new Date());
            productsMapper.updateByPrimaryKey(products);
        } else {
            new Exception("数量不对");
        }

        return shoppingCartMapper.updateByPrimaryKey(shoppingCart);
    }
}
