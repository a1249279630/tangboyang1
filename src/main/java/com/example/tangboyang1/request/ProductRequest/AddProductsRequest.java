package com.example.tangboyang1.request.ProductRequest;

import lombok.Data;

@Data
public class AddProductsRequest {
    private String name;

    private Double price;

    private String category;

    private Integer pnum;

    private String imgurl;

    private String description;

//    private Integer store_id;
}
