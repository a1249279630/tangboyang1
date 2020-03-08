package com.example.tangboyang1.controller;//package com.example.tangboyang.controller;
//
//import com.example.tangboyang.pojo.Orders;
//import com.example.tangboyang.request.AddOrdersRequest;
//import com.example.tangboyang.service.OrderService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//public class OrderController {
//    @Autowired
//    private OrderService orderService;
//    @GetMapping(value = "find/all/order")
//    public List<Orders> findAllOrders(Integer PageNumber,Integer PageSize){
//        return orderService.findAllOrder(PageNumber,PageSize);
//    }
//
//    @PostMapping(value = "add/orders")
//    public String addOrders(@RequestBody AddOrdersRequest addOrdersRequest,Integer id){
//        Integer integer = orderService.addOrder(addOrdersRequest,id);
//        if(integer==1){
//            return "增加成功";
//        }else {
//            return "增加失败，这个产品id没有";
//        }
//    }
//
//    /*修改订单支付状态*/
//    @PostMapping(value = "update/orders/payState/by/id")
//    public Integer updateOrderpaystateByid(Integer id){
//        return orderService.updateOrderpaystateByid(id);
//    }
//
//    @DeleteMapping(value = "delete/orders/by/id")
//    public String deleteOrders(@RequestBody Integer id) {
//        Integer integer = orderService.deleteOrder(id);
//        if(integer==1){
//            return "删除成功";
//        }else if(integer==-1){
//            return "删除失败，订单已支付";
//        }else {
//            return "未找到此id";
//        }
//    }
//
////    @PostMapping(value = "update/order/by/order")
////    public Integer updateOrders(@RequestBody Orders orders) {
////        return orderService.updateOrder(orders);
////    }
//
//    @DeleteMapping(value = "delete/order/by/userid")
//    public String deleteOrdersByUserid(Integer userid) {
//        Integer integer = orderService.deleteOrdersByUserid(userid);
//        return "成功删除该用户"+integer+"件未支付的订单";
//    }
//
//    @GetMapping(value = "find/orders/by/Orderid")
//    public Orders findOrderByOrderId(Integer orderId) {
//        return orderService.findOrderByOrderId(orderId);
//    }
//
//    @GetMapping(value = "find/order/by/Userid")
//    public List<Orders> findOrdersByUserId(Integer userid){
//        return orderService.findOrdersByUserId(userid);
//    }
//}
