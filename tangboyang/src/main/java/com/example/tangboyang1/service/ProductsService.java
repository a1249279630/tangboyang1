package com.example.tangboyang1.service;


import com.example.tangboyang1.request.ProductRequest.AddProductsRequest;
import com.example.tangboyang1.request.ProductRequest.UpdateProductsRequest;
import com.example.tangboyang1.response.FindCategoryAndCategoryid;
import com.example.tangboyang1.response.FindProductResponse;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public interface ProductsService {
    Integer addProducts(AddProductsRequest addProductsRequest);

    Integer deleteProducts(Integer id);

    Integer updateProducts(UpdateProductsRequest updateProductsRequest, Integer id);

    List<FindProductResponse> findAllProducts(Integer pageNumber, Integer pageSize);

    Integer updatePNum(int num, Integer id);

    long count(String category);

    FindProductResponse findBookById(Integer id);

    List<FindProductResponse> findProductByBookName(Integer pageNum, Integer pageSize, String bookName);

    List<FindProductResponse> SelectCategoryBookByCategory(HttpServletRequest request, Integer id);

    List<FindCategoryAndCategoryid> findAllCategory();

    List<FindProductResponse> RandomBook();
}
