package com.example.tangboyang1.service.impl;

import com.example.tangboyang1.dao.ImageDao;
import com.example.tangboyang1.dao.ProductDao;
import com.example.tangboyang1.pojo.Image;
import com.example.tangboyang1.pojo.Products;
import com.example.tangboyang1.request.ProductRequest.AddProductsRequest;
import com.example.tangboyang1.request.ProductRequest.UpdateProductsRequest;
import com.example.tangboyang1.response.FindCategoryAndCategoryid;
import com.example.tangboyang1.response.FindImageResponse;
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
    @Autowired
    private ImageDao imageDao;
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

        String imgurs = apr.getImgurl();
        String[] split = imgurs.split("\\,");

        String uuid=UUID.randomUUID().toString();
        Integer num1=1;
        for (String s:split) {
            Image image=new Image();
            image.setProductsUuid(uuid);
            image.setImagurlNum(num1++);
            image.setImagurl(s);
            imageDao.AddImage(image);
        }
        products.setCategory(apr.getCategory());
        products.setPnum(apr.getPnum());
        products.setDescription(apr.getDescription());
        products.setImgurl(split[0]);
        products.setName(apr.getName());
        products.setPrice(apr.getPrice());
        products.setCreattime(new Date());
        products.setCurrentprice(apr.getPrice()-n);
        products.setStoreId(1);
        products.setUuid(uuid);
        return productDao.addProducts(products);
    }

    @Override
    public Integer deleteProducts(Integer id) {
        Products bookById = productDao.findBookById(id);
        Integer integer = imageDao.deleteImageByUuid(bookById.getUuid());
        if(integer==1){
            return productDao.deleteProducts(id);
        }
        return -1;
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
//        return productDao.updatePNum(num,id);
        return null;
    }

    @Override
    public long count(String category) {
        return productDao.count(category);
    }

    @Override
    public FindProductResponse findBookById(Integer id) {
        Products bookById = productDao.findBookById(id);
        List<Products> products=new ArrayList<>();
        products.add(bookById);
//        FindProductResponse findProductResponse=new FindProductResponse();
//        BeanUtils.copyProperties(bookById,findProductResponse);
        List<FindProductResponse> findProductResponses = ShowFindProductResponse(products);

        return  findProductResponses.get(0);
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
        List<Products> all2 = new ArrayList<>();
        int size = all.size();
        System.out.println(size);
        for(int i=0;i<6;i++){
            try {
                int v =(int) (Math.random() * size);
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
//            if(product.getUuid()==null||!product.getUuid().equals("")) {
//            if(!product.getUuid().isEmpty()){
            try{
                List<Image> images = imageDao.FindimageByProductUuid(product.getUuid());
                List<FindImageResponse> findImageResponses=new ArrayList<>();
                for (Image f:images) {
                    FindImageResponse findImageResponse=new FindImageResponse();
                    findImageResponse.setId(f.getImagurlNum());
                    findImageResponse.setImagurl(f.getImagurl());
                    findImageResponses.add(findImageResponse);
                }
                findProductResponse.setImages(findImageResponses);
            }catch (Exception e){}
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
