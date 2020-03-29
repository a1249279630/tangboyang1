package com.example.tangboyang1.service.impl;

import com.example.tangboyang1.dao.OrderDao;
import com.example.tangboyang1.dao.ProductDao;
import com.example.tangboyang1.dto.ResultDTO;
import com.example.tangboyang1.error.CommonErrorCode;
import com.example.tangboyang1.error.ErrorCodeException;
import com.example.tangboyang1.pojo.Orders;
import com.example.tangboyang1.pojo.Products;
import com.example.tangboyang1.pojo.User;
import com.example.tangboyang1.request.OrderRequest.AddOrdersRequest;
import com.example.tangboyang1.response.FindAllUserOrderResponse;
import com.example.tangboyang1.service.OrderService;
import com.example.tangboyang1.session.SessionUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private  ProductDao productDao;

    @Override
    @Transactional
    public Integer addOrder(AddOrdersRequest aor) {
        try {
            User user = SessionUtil.getUser();
            Products products = productDao.findBookById(aor.getProductId());
            if(products.getPnum()<aor.getNum()){
                throw new ErrorCodeException(CommonErrorCode.Product_Num_Error);
            }
            Orders orders=new Orders();
            orders.setUserId(user.getId());
            orders.setNum(aor.getNum());
            orders.setProductId(aor.getProductId());
            orders.setAddressId(aor.getAddressId());
            orders.setOrdertime(new Date());
            orders.setPaystate(0);
            orders.setMoney (products.getCurrentprice()*aor.getNum());
            orderDao.addOrders(orders);
            System.out.println(orders.getId());
            products.setPnum(products.getPnum()-aor.getNum());
            productDao.updateProducts(products);
            return orders.getId();
        }catch (ErrorCodeException e){
            e.printStackTrace();
            throw new ErrorCodeException(CommonErrorCode.Product_Num_Error);
        }catch (Exception e){
            e.printStackTrace();
            throw new ErrorCodeException(CommonErrorCode.UNKOWN_ERROR);
        }

    }
    @Override
    public Integer deleteOrder(Integer id) {
        try{
            Orders orderByOrderId = orderDao.findOrderByOrderId(id);
            if(orderByOrderId.getPaystate()==0){
                orderDao.deleteOrders(id);
                return 1;
            }else{
                return -1;
            }
        }catch (Exception e){
            return -2;
        }

    }

    @Override
    public Integer updateOrder(Orders orders) {
        return orderDao.updateOrders(orders);
    }

    @Override
    public List<FindAllUserOrderResponse> findAllOrder(Integer PageNumber,Integer PageSize) {
        PageHelper.startPage(PageNumber,PageSize);
        List<Orders> all=orderDao.findAllOrders();
        List<FindAllUserOrderResponse> findAllUserOrderResponses = ShowOrder(all);
        PageInfo<FindAllUserOrderResponse> pageInfo=new PageInfo<>(findAllUserOrderResponses);
        return pageInfo.getList();
    }

    @Override
    public Integer deleteOrdersByUserid() {
        User user = SessionUtil.getUser();
        List<Orders> ordersByUserId = orderDao.findOrdersByUserId(user.getId());
        int notPay=0;
        for(Orders orders:ordersByUserId){
            if(orders.getPaystate()==0){
                orderDao.deleteOrdersByUserid(user.getId());
                notPay++;
            }
        }
        return notPay;
    }

    @Override
    public FindAllUserOrderResponse findOrderByOrderId(Integer orderId) {
        Orders orderByOrderId = orderDao.findOrderByOrderId(orderId);
        List<Orders> orders=new ArrayList<>();
        orders.add(orderByOrderId);
        List<FindAllUserOrderResponse> findAllUserOrderResponses = ShowOrder(orders);
        return findAllUserOrderResponses.get(0);
    }

    @Override
    public List<FindAllUserOrderResponse> findOrdersByUserId() {
        User user = SessionUtil.getUser();
        List<Orders> ordersByUser = orderDao.findOrdersByUserId(user.getId());
        return ShowOrder(ordersByUser);
    }

    @Override
    public Integer updateOrderpaystateByid(Integer id) {
        Orders order = orderDao.findOrderByOrderId(id);
        order.setPaystate(1);
        return orderDao.updateOrders(order);
    }


    public List<FindAllUserOrderResponse> ShowOrder(List<Orders> orders){
        List<FindAllUserOrderResponse> findAllUserOrderResponses= new ArrayList<>();
        for (Orders o:orders) {
            FindAllUserOrderResponse findAllUserOrderResponse=new FindAllUserOrderResponse();
            BeanUtils.copyProperties(o,findAllUserOrderResponse);
            Products bookBy = productDao.findBookById(o.getProductId());
            findAllUserOrderResponse.setImgurl(bookBy.getImgurl());
            BeanUtils.copyProperties(bookBy,findAllUserOrderResponse);
            findAllUserOrderResponse.setId(o.getId());
            findAllUserOrderResponses.add(findAllUserOrderResponse);
        }
        return findAllUserOrderResponses;
    }
}
