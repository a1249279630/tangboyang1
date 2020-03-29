package com.example.tangboyang1.dao;

import com.example.tangboyang1.mapper.ProductsMapper;
import com.example.tangboyang1.pojo.Products;
import com.example.tangboyang1.pojo.ProductsExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDao {
    @Autowired
    private ProductsMapper productsMapper;

    public List<Products> findAllProducts(String category){
        ProductsExample productsExample=new ProductsExample();
        if(!category.equals("121")){
            productsExample.createCriteria().andCategoryEqualTo(category);
        }

        return productsMapper.selectByExample(productsExample);
    }

    public Integer addProducts(Products products){
        return productsMapper.insertSelective(products);
    }

    public Integer deleteProducts(Integer id){
        return productsMapper.deleteByPrimaryKey(id);
    }
    public Integer updateProducts(Products products){
        return productsMapper.updateByPrimaryKey(products);
    }

    public Products findBookById(Integer id)  {
        Products products = productsMapper.selectByPrimaryKey(id);
        if(products==null||products.equals("")){
            return null;
        }
        return products;
    }

    //category 如果是null，是表的所所有记录数
    public long count(String category)   {
        ProductsExample productsExample=new ProductsExample();
        productsExample.createCriteria().andCategoryEqualTo(category);
        return productsMapper.countByExample(productsExample);
    }

//    public Integer updatePNum(int num,Integer id) {
//
//        Products products = productsMapper.selectByPrimaryKey(id);
//        if(products.getPnum()>num&&products.getPnum()>0){
//            products.setPnum(products.getPnum()-num);
//        }else{
//            new Exception("数量不够");
//        }
//        return productsMapper.updateByPrimaryKey(products);
//    }

    public List<Products> findBookByName(String bookname) {
        ProductsExample productsExample=new ProductsExample();
        productsExample.createCriteria().andNameLike("%"+bookname+"%");
        return productsMapper.selectByExample(productsExample);
    }


    public List<Products> findAllCategory() {
        ProductsExample productsExample=new ProductsExample();
        productsExample.setDistinct(true);
        return productsMapper.selectByExample(productsExample);

//        return productsMapper.findAllCategory();


    }

    public List<Products> findProductByCategory(String category) {
        ProductsExample productsExample=new ProductsExample();
        productsExample.createCriteria().andCategoryEqualTo(category);

        return productsMapper.selectByExample(productsExample);
    }

    public List<Products> findBookByCategoryId(Integer id) {
        ProductsExample productsExample=new ProductsExample();
        productsExample.createCriteria().andCategoryIdEqualTo(id);
        return productsMapper.selectByExample(productsExample);

    }


//    public List<Products> RandomBook() {
//        return productDao.findAllProducts("121");
//    }
}
