package com.example.tangboyang1.service;

import com.example.tangboyang1.dto.ResultDTO;
import com.example.tangboyang1.pojo.Orders;
import com.example.tangboyang1.request.OrderRequest.AddOrdersRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    ResultDTO addOrder(AddOrdersRequest aor);
    Integer deleteOrder(Integer id);

    Integer updateOrder(Orders orders);

    List<Orders> findAllOrder(Integer PageNumber, Integer PageSize);

    Integer deleteOrdersByUserid();

    Orders findOrderByOrderId(Integer OrderId);

    List<Orders> findOrdersByUserId();

    Integer updateOrderpaystateByid(Integer id);
}
