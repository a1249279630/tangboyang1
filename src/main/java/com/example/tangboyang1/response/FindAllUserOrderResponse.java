package com.example.tangboyang1.response;

import lombok.Data;

import java.util.Date;
@Data
public class FindAllUserOrderResponse {
    private Integer id;

    private Integer userId;

    private Integer storeId;

    private Double money;

    private Integer paystate;

    private Date ordertime;

    private Integer productId;

    private Integer num;

    private String imgurl;

    private String description;

    private Double currentprice;;
}
