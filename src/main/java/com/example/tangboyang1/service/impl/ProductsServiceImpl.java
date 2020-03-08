package com.example.tangboyang1.service.impl;

import com.example.tangboyang1.dao.ProductDao;
import com.example.tangboyang1.pojo.Products;
import com.example.tangboyang1.request.ProductRequest.AddProductsRequest;
import com.example.tangboyang1.request.ProductRequest.UpdateProductsRequest;
import com.example.tangboyang1.response.FindCategoryAndCategoryid;
import com.example.tangboyang1.response.FindProductResponse;
import com.example.tangboyang1.service.ProductsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductsServiceImpl implements ProductsService {
    @Autowired
    private ProductDao productDao;
    @Override
    public Integer addProducts(AddProductsRequest apr) {
        DecimalFormat num = new DecimalFormat("#.00");
        int d= (int) ((apr.getPrice())/5);
        String random=num.format((Math.random()*d));
        double n=Double.parseDouble(random);
        Products products=new Products();
        List<Products> productByCategory = productDao.findProductByCategory(apr.getCategory());
        if(CollectionUtils.isEmpty(productByCategory)){
            int size = findAllCategory().size();
            products.setCategoryId(size+1);
        }else {
            products.setCategoryId(productByCategory.get(0).getCategoryId());
        }

        products.setCategory(apr.getCategory());
        products.setPnum(apr.getPnum());
        products.setDescription(apr.getDescription());
        products.setImgurl(apr.getImgurl());
        products.setName(apr.getName());
        products.setPrice(apr.getPrice());
        products.setCreattime(new Date());
        products.setCurrentprice(apr.getPrice()-n);
        products.setStoreId(1);
        return productDao.addProducts(products);
    }

    @Override
    public Integer deleteProducts(Integer id) {
        return productDao.deleteProducts(id);
    }

    @Override
    public Integer updateProducts(UpdateProductsRequest upr, Integer id) {
        Products products = productDao.findBookById(id);
        products.setCategory(upr.getCategory());
        products.setPnum(upr.getPnum());
        products.setDescription(upr.getDescription());
        products.setImgurl(upr.getImgurl());
        products.setName(upr.getName());
        products.setPrice(upr.getPrice());
        products.setCreattime(new Date());
        return productDao.updateProducts(products);
    }

    @Override
    public List<FindProductResponse> findAllProducts(Integer pageNumber,Integer pageSize) {
        PageHelper.startPage(pageNumber,pageSize);
        List<Products> all=productDao.findAllProducts("121");
        List<FindProductResponse> findProductResponses = ShowFindProductResponse(all);
        PageInfo<FindProductResponse> pageInfo=new PageInfo<>(findProductResponses);

        return pageInfo.getList();

    }

    @Override
    public Integer updatePNum(int num, Integer id) {
        return productDao.updatePNum(num,id);
    }

    @Override
    public long count(String category) {
        return productDao.count(category);
    }

    @Override
    public FindProductResponse findBookById(Integer id) {
        Products bookById = productDao.findBookById(id);
        FindProductResponse findProductResponse=new FindProductResponse();
        BeanUtils.copyProperties(bookById,findProductResponse);
        return  findProductResponse;
    }

    @Override
    public List<FindProductResponse> findProductByBookName(Integer pageNum,Integer pageSize,String bookName) {

        PageHelper.startPage(pageNum,pageSize);
        List<Products> all = productDao.findBookByName(bookName);
        PageInfo<Products> pageInfo=new PageInfo<>(all);
        List<Products> products = pageInfo.getList();
        return ShowFindProductResponse(products);
    }

    @Override
    public List<FindProductResponse> SelectCategoryBookByCategory(HttpServletRequest request,Integer id) {
//        String attribute = (String) request.getSession().getAttribute(""+id);
//        System.out.println(attribute);
//        List<Products> allProducts = productDao.findAllProducts(attribute);
        List<Products> allProducts =productDao.findBookByCategoryId(id);
        List<FindProductResponse> findProductResponses = ShowFindProductResponse(allProducts);
        return findProductResponses;
    }


//    public List<String> findAllCategoryAndCategoryid() {
//       List<FindCategoryAndCategoryid> findCategoryAndCategoryids=findAllCategory();
////        FindCategoryAndCategoryid []findCategoryAndCategoryid={};
//
//        for (String a:allCategory){
//            int id = Integer.parseInt(a);
//        }
//
//    }

    @Override
    public List<FindProductResponse> RandomBook() {
        List<Products> all=productDao.findAllProducts("121");
        System.out.println(all.toString());
        List<Products> all2 = new ArrayList<>();
        int size = all.size();
        System.out.println(size);
        for(int i=0;i<6;i++){
            try {
                int v =(int) (Math.random() * size);
                System.out.println(v);
                System.out.println(all.get(v));
                all2.add(all.get(v));
                all.remove(v);
                size=size-1;
            }catch (Exception e){ }
        }
        List<FindProductResponse> findProductResponses = ShowFindProductResponse(all2);
        return findProductResponses;
    }

    private List<FindProductResponse> ShowFindProductResponse(List<Products> products) {
        List<FindProductResponse> productResponseList=new ArrayList<>();
        for(Products product:products){
            FindProductResponse findProductResponse=new FindProductResponse();
            BeanUtils.copyProperties(product,findProductResponse);
            productResponseList.add(findProductResponse);
        }
        return productResponseList;
    }
    @Override
    public List<FindCategoryAndCategoryid> findAllCategory() {
        List<Products> allCategory = productDao.findAllCategory();
        List<String> list=new ArrayList<>();
        List<Integer> list1=new ArrayList<>();
        for (Products p:allCategory) {
            list.add(p.getCategory());
            list1.add(p.getCategoryId());
        }
        List<String> list2=list.stream().distinct().collect(Collectors.toList());
        List<Integer> list3= list1.stream().distinct().collect(Collectors.toList());
        List<FindCategoryAndCategoryid> l=new ArrayList<>();
        for(int i=0;i<list2.size();i++){
            FindCategoryAndCategoryid findCategoryAndCategoryid=new FindCategoryAndCategoryid();
            findCategoryAndCategoryid.setCategory(list2.get(i));
            findCategoryAndCategoryid.setCategoryid(list3.get(i));
            l.add(findCategoryAndCategoryid);
        }
        return l;
    }

}
