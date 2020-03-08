package com.example.tangboyang1.dao;

import com.example.tangboyang1.mapper.OrdersMapper;
import com.example.tangboyang1.pojo.Orders;
import com.example.tangboyang1.pojo.OrdersExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDao {
    @Autowired
    private OrdersMapper ordersMapper;

    public List<Orders> findAllOrders(){
        OrdersExample ordersExample=new OrdersExample();
        return ordersMapper.selectByExample(ordersExample);
    }

    public Integer addOrders(Orders orders){
        return ordersMapper.insert(orders);
    }
    /*通过id删除订单*/
    public Integer deleteOrders(Integer id){
        return ordersMapper.deleteByPrimaryKey(id);
    }
    public Integer updateOrders(Orders orders){
        return ordersMapper.updateByPrimaryKey(orders);
    }
    /*通过Userid删除订单*/
    public Integer deleteOrdersByUserid(Integer userid){
        OrdersExample ordersExample=new OrdersExample();
        ordersExample.createCriteria().andUserIdEqualTo(userid);
        return ordersMapper.deleteByExample(ordersExample);
    }
    public Orders findOrderByOrderId(Integer OrderId){
        return ordersMapper.selectByPrimaryKey(OrderId);
    }
    public List<Orders> findOrdersByUserId(Integer userid){
        OrdersExample ordersExample=new OrdersExample();
        ordersExample.createCriteria().andUserIdEqualTo(userid);
        return ordersMapper.selectByExample(ordersExample);
    }
}
