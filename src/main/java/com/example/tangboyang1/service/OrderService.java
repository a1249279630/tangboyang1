package com.example.tangboyang1.service;

import com.example.tangboyang1.dto.ResultDTO;
import com.example.tangboyang1.pojo.Orders;
import com.example.tangboyang1.request.OrderRequest.AddOrdersRequest;
import com.example.tangboyang1.response.FindAllUserOrderResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    ResultDTO addOrder(AddOrdersRequest aor);
    Integer deleteOrder(Integer id);

    Integer updateOrder(Orders orders);

    List<FindAllUserOrderResponse> findAllOrder(Integer PageNumber, Integer PageSize);

    Integer deleteOrdersByUserid();

    FindAllUserOrderResponse findOrderByOrderId(Integer OrderId);

    List<FindAllUserOrderResponse> findOrdersByUserId();

    Integer updateOrderpaystateByid(Integer id);
}
