package com.example.tangboyang1.controller;//package com.example.tangboyang.controller;

import com.example.tangboyang1.request.ProductRequest.AddProductsRequest;
import com.example.tangboyang1.request.ProductRequest.UpdateProductsRequest;
import com.example.tangboyang1.response.FindCategoryAndCategoryid;
import com.example.tangboyang1.response.FindProductResponse;
import com.example.tangboyang1.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Component
@RestController
public class ProductController {
    @Autowired
    private ProductsService productsService;
    @GetMapping(value = "find/all/Book")
    public List<FindProductResponse> findAllProduct(Integer pageNumber, Integer pageSize){
        return productsService.findAllProducts(pageNumber,pageSize);
    }

    @PostMapping(value = "add/product")
    public Integer addProduct(@RequestBody AddProductsRequest addProductsRequest){
        return productsService.addProducts(addProductsRequest);
    }

    /*通过产品名查找产品*/
    @PostMapping(value = "Find/Book/by/bookname")
    public List<FindProductResponse> findProductByBookName(@RequestBody Integer pageNumber,Integer pageSize,String bookName){
        return productsService.findProductByBookName(pageNumber,pageSize,bookName);
    }

    @DeleteMapping(value = "delete/Book/by/id")
    public Integer deleteProduct(@RequestBody Integer id) {
        return productsService.deleteProducts(id);
    }

    @PostMapping(value = "update/Book/by/Bookid")
    public Integer updateProduct(@RequestBody UpdateProductsRequest updateProductsRequest,Integer id) {
        return productsService.updateProducts(updateProductsRequest,id);
    }

    @PostMapping(value = "update/BookNum/by/id")
    public Integer updatePNum(@RequestBody int num, Integer id) {
        return productsService.updatePNum(num,id);
    }

    @GetMapping(value = "select/count")
    public long count(@RequestBody String category) {
        return productsService.count(category);
    }

    @GetMapping(value = "select/Book/by/id")
    public FindProductResponse findBookById(Integer id) {
        return productsService.findBookById(id);
    }


    @GetMapping(value = "Find/Book/By/Category")
    public List<FindProductResponse> SelectCategoryBookByCategory(HttpServletRequest request,Integer id){
       return productsService.SelectCategoryBookByCategory(request,id);
    }

    @GetMapping(value = "Find/all//Category")
    public List<FindCategoryAndCategoryid> findAllCategory(HttpServletRequest request){

        return productsService.findAllCategory();
    }

    @GetMapping(value = "RandomFind/book * 6")
    public List<FindProductResponse> RandomBook(){
        return productsService.RandomBook();
    }

}


