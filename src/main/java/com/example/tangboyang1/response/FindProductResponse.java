package com.example.tangboyang1.response;

import lombok.Data;

import java.util.List;

@Data
public class FindProductResponse {
    private Integer id;

    private String name;

    private Double price;

    private String category;

    private Integer pnum;

    private String description;

    private Double currentprice;

    private List<FindImageResponse> images;




}
