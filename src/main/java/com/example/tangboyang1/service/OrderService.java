package com.example.tangboyang1.service;

import com.example.tangboyang1.pojo.Orders;
import com.example.tangboyang1.request.OrderRequest.AddOrdersRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    Integer addOrder(AddOrdersRequest addOrdersRequest, Integer id);
    Integer deleteOrder(Integer id);

    Integer updateOrder(Orders orders);

    List<Orders> findAllOrder(Integer PageNumber, Integer PageSize);

    Integer deleteOrdersByUserid(Integer userid);

    Orders findOrderByOrderId(Integer OrderId);

    List<Orders> findOrdersByUserId(Integer userid);

    Integer updateOrderpaystateByid(Integer id);
}
