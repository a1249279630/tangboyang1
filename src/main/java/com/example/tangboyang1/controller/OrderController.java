package com.example.tangboyang1.controller;//package com.example.tangboyang.controller;

import com.example.tangboyang1.dto.ResultDTO;
import com.example.tangboyang1.pojo.Orders;
import com.example.tangboyang1.request.OrderRequest.AddOrdersRequest;
import com.example.tangboyang1.response.FindAllUserOrderResponse;
import com.example.tangboyang1.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("OrderController")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @GetMapping(value = "find/all/order")
    public List<FindAllUserOrderResponse> findAllOrders(Integer PageNumber,Integer PageSize){
        return orderService.findAllOrder(PageNumber,PageSize);
    }

    @PostMapping(value = "add/orders")
    public ResultDTO addOrders(AddOrdersRequest addOrdersRequest){

      return orderService.addOrder(addOrdersRequest);
//        if(integer==1){
//            return ResultDTO.ok("增加成功");
//        }else {
//            return "增加失败，这个产品id没有";
//        }
    }

    /*修改订单支付状态*/
    @PostMapping(value = "update/orders/payState/by/Orderid")
    public Integer updateOrderpaystateByid(Integer id){
        return orderService.updateOrderpaystateByid(id);
    }

    @DeleteMapping(value = "delete/orders/by/Orderid")
    public String deleteOrders(Integer id) {
        Integer integer = orderService.deleteOrder(id);
        if(integer==1){
            return "删除成功";
        }else if(integer==-1){
            return "删除失败，订单已支付";
        }else {
            return "未找到此id";
        }
    }

    @DeleteMapping(value = "delete/order/by/userid")
    public String deleteOrdersByUserid() {
        Integer integer = orderService.deleteOrdersByUserid();
        return "成功删除该用户"+integer+"件未支付的订单";
    }

    @GetMapping(value = "find/orders/by/Orderid")
    public FindAllUserOrderResponse findOrderByOrderId(Integer orderId) {
        return orderService.findOrderByOrderId(orderId);
    }

    @GetMapping(value = "find/order/by/Userid")
    public List<FindAllUserOrderResponse> findOrdersByUserId( ){
        return orderService.findOrdersByUserId();
    }
}
