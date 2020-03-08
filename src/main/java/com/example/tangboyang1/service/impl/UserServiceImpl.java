package com.example.tangboyang1.service.impl;

import com.example.tangboyang1.dao.OrderDao;
import com.example.tangboyang1.dao.StoreDao;
import com.example.tangboyang1.dao.UserDao;
import com.example.tangboyang1.pojo.Orders;
import com.example.tangboyang1.pojo.Store;
import com.example.tangboyang1.pojo.User;
import com.example.tangboyang1.request.UserRequest.RegistUserRequest;
import com.example.tangboyang1.request.UserRequest.UpdateUserPasswardRequest;
import com.example.tangboyang1.request.UserRequest.UpdateUserRequest;
import com.example.tangboyang1.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service

public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private StoreDao storeDao;
    @Override
    public Integer addUser(RegistUserRequest registUserRequest) {
        List<User> userByUserName = userDao.findUserByUserName(registUserRequest.getUsername());
        if(CollectionUtils.isEmpty(userByUserName))
          {
        User user=new User();
        user.setEmail(registUserRequest.getEmail());
        user.setGender(registUserRequest.getGender());
        user.setIntroduce(registUserRequest.getIntroduce());
        user.setPassword(registUserRequest.getPassword());
        user.setTelephone(registUserRequest.getTelephone());
        user.setUsername(registUserRequest.getUsername());
        user.setActivecode(randomSafetyCode());
        user.setRegisttime(new Date());
        user.setState(0);
        user.setRole("普通用户");
        userDao.addUser(user);
        return 1;
        }else{
            return -1;
        }
    }

    @Override
    public Integer deleteUser(Integer id) {
        List<Orders> order = orderDao.findOrdersByUserId(id);
        if(CollectionUtils.isEmpty(order)){
            userDao.deleteUser(id);
            return 1;
        }else {
            return -1;
        }

    }

    @Override
    public Integer updateUser(HttpServletRequest request,UpdateUserRequest updateUserRequest, Integer id) {
        User user = userDao.findUserById(id);
        BeanUtils.copyProperties(updateUserRequest,user);
        user.setId(id);
        return userDao.updateUser(user);
    }

//    @Override
    public List<User> findAllUser(Integer pageNumber,Integer pageSize) {
        PageHelper.startPage(pageNumber,pageSize);
        List<User> all=userDao.findAllUser();
        PageInfo<User> pageInfo=new PageInfo<>(all);
        return pageInfo.getList();
    }
//
    @Override
    public Integer updateState(String activeCode) {
         User user=new User();
         /*BeanUtils.copyProperties(updateUserStateRequest,user);*/
         user.setState(1);
         user.setActivecode(activeCode);
         return userDao.updateState(user,activeCode);
    }

    @Override
    public User findUserById(int id) {
        return userDao.findUserById(id);
    }

    @Override
    public String findUserByUserNameandPassword(HttpServletRequest request,String username, String password) {

        User user = userDao.findUserByUserNameandPassword111(username, password);

//        request.getSession().setAttribute("userid",user.getId());
        if(user.getRole().equals("普通用户")||user.getRole().equals("卖家用户")){


            if(user.getRole().equals("卖家用户")){

                List<Store> stores = storeDao.findStoreByid(user.getId());
                Store store = stores.get(0);
                String temp="可以上传商品";
                return user.toString()+store.toString()+"主页"+temp;
            }
            return user.toString();
        }else {
            return user.toString()+"管理员页";
        }
    }

    @Override
    public List<User> findUserByActiveCode(String activeCode) {
        return userDao.findUserByActiveCode(activeCode);
    }

    @Override
    public Integer updateUserPassword(HttpServletRequest request,UpdateUserPasswardRequest updateUserPasswardRequest) {

        List<User> users = userDao.findUserByUserName(updateUserPasswardRequest.getUsername());
        User user = new User();
        if (!CollectionUtils.isEmpty(users)){
            user = users.get(0);
        }
        if(updateUserPasswardRequest.getActivecode().equals(user.getActivecode())){
            if(updateUserPasswardRequest.getPassword().equals(user.getPassword())){
                return -1;//新密码不能与旧密码一样
            }else{
                if(updateUserPasswardRequest.getCopypassword().equals(updateUserPasswardRequest.getPassword())){
                    user.setPassword(updateUserPasswardRequest.getPassword());
                    userDao.updateUserbyUsername(user,updateUserPasswardRequest.getUsername());
                    return 1;//密码更改成功
                }else{
                    return 0;//两次密码不一致
                }
            }
        }else return -2;//安全码错误


    }

    @Override
    public Integer updateUserRole(String userrole, Integer id) {
        try {
            User user = userDao.findUserById(id);
            user.setRole(userrole);
            userDao.updateUser(user);
            return 1;
        }catch (Exception e){
            return -1;
        }

    }

    @Override
    public User findUserByOpenid(String openid) {

                List<User> userByOpenId = userDao.findUserByOpenId(openid);
                if(CollectionUtils.isEmpty(userByOpenId)){
                    return null;
                }
                    return userByOpenId.get(0);

    }

    @Override
    public Integer saveOrUpdate(User user) {
        List<User> userByOpenId = userDao.findUserByOpenId(user.getOpenId());
        if(CollectionUtils.isEmpty(userByOpenId)){
            user.setActivecode(randomSafetyCode());
            user.setRegisttime(new Date());
            user.setState(0);
            user.setRole("普通用户");
            System.out.println(user.getOpenId());
            return userDao.addUser(user);
        }else {
           return userDao.updateUser(user);
        }
    }

    @Override
    public User FindUserByToken(String token) {
        List<User> users = userDao.FindUserByToken(token);
        if(CollectionUtils.isEmpty(users)){
            return null;
        }
        return users.get(0);
    }

    public String randomSafetyCode(){
        char[] str = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
        int count = 0;
        int i;
        int maxNum = 62;
        StringBuffer stringBuffer = new StringBuffer();
        Random random = new Random();
        while(count < 8){
            i = Math.abs(random.nextInt(maxNum));
            if (i >= 0 && i < str.length) {
                stringBuffer.append(str[i]);
                count ++;
            }
        }
        return stringBuffer.toString();
    }
}
