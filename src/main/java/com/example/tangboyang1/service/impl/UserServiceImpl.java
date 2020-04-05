package com.example.tangboyang1.service.impl;

import com.example.tangboyang1.adapter.WechatAdapter;
import com.example.tangboyang1.dao.OrderDao;
import com.example.tangboyang1.dao.StoreDao;
import com.example.tangboyang1.dao.UserDao;
import com.example.tangboyang1.dto.LoginDTO;
import com.example.tangboyang1.dto.ResultDTO;
import com.example.tangboyang1.dto.SessionDTO;
import com.example.tangboyang1.error.CommonErrorCode;
import com.example.tangboyang1.error.ErrorCodeException;
import com.example.tangboyang1.pojo.Orders;
import com.example.tangboyang1.pojo.Store;
import com.example.tangboyang1.pojo.User;
import com.example.tangboyang1.request.UserRequest.RegistUserRequest;
import com.example.tangboyang1.request.UserRequest.UpdateUserPasswardRequest;
import com.example.tangboyang1.request.UserRequest.UpdateUserRequest;
import com.example.tangboyang1.response.FindAllUserResponse;
import com.example.tangboyang1.service.UserService;
import com.example.tangboyang1.session.SessionUtil;
import com.example.tangboyang1.utils.JsonUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private StoreDao storeDao;
    @Autowired
    private WechatAdapter wechatAdapter;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
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
    public Integer updateUser(UpdateUserRequest updateUserRequest) {
        User user1 = SessionUtil.getUser();
        User user = userDao.findUserById(user1.getId());
        BeanUtils.copyProperties(updateUserRequest,user);
        return userDao.updateUser(user);
    }

    @Override
    public List<FindAllUserResponse> findAllUser(Integer pageNumber, Integer pageSize) {
        PageHelper.startPage(pageNumber,pageSize);
        List<User> all=userDao.findAllUser();
        List<FindAllUserResponse> allUserResponses=new ArrayList<>();
        for (User u:all) {
            List<Store> store = storeDao.findStoreByUserid(u.getId());
            FindAllUserResponse response=new FindAllUserResponse();
            if(CollectionUtils.isEmpty(store)){
                response.setStore(null);
            }else {
                response.setStore(store.get(0));
            }
            response.setUser(u);
            allUserResponses.add(response);
        }
        PageInfo<FindAllUserResponse> pageInfo=new PageInfo<>(allUserResponses);
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
    public ResultDTO findUserByUserNameandPassword(String username, String password) {
        try {
        User user = userDao.findUserByUserNameandPassword111(username, password);
        String token= UUID.randomUUID().toString();

        String tpstring=JsonUtils.objectToJson(user);
        if(!StringUtils.isEmpty(user.getToken())){
            String value=stringRedisTemplate.opsForValue().get(user.getToken());
            if(!StringUtils.isEmpty(value)){
                stringRedisTemplate.opsForValue().set(user.getToken(),tpstring,2, TimeUnit.DAYS);
                user.setToken(user.getToken());
                token=user.getToken();
            }else {
                stringRedisTemplate.opsForValue().set(token,tpstring,2, TimeUnit.DAYS);
                user.setToken(token);
            }
        }else {
            user.setToken(token);
            stringRedisTemplate.opsForValue().set(token,tpstring,2, TimeUnit.DAYS);
        }
        userDao.updateUser(user);
        if(user.getRole().equals("普通用户")||user.getRole().equals("卖家用户")){
            if(user.getRole().equals("卖家用户")){
                List<Store> stores = storeDao.findStoreByUserid(user.getId());
                Store store = stores.get(0);
                String temp="可以上传商品";
                return ResultDTO.ok("卖家用户"+token);
            }
            return ResultDTO.ok("普通用户"+token);
        }else {
            return ResultDTO.ok("管理员"+token);
        }
        } catch (ErrorCodeException e) {
            return ResultDTO.fail(e);
        } catch (Exception e) {
            return ResultDTO.fail(CommonErrorCode.UNKOWN_ERROR);
        }
    }

    @Override
    public List<User> findUserByActiveCode(String activeCode) {
        return userDao.findUserByActiveCode(activeCode);
    }

    @Override
    public Integer updateUserPassword(UpdateUserPasswardRequest updateUserPasswardRequest) {
        User user1 = SessionUtil.getUser();
        List<User> users = userDao.findUserByUserName(user1.getUsername());
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
                    userDao.updateUserbyUsername(user,user1.getUsername());
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
    public ResultDTO findUserByOpenid(LoginDTO loginDTO) {
        try {
            User user;
            Integer integer =0,num=0;
            String value ="";
            SessionDTO sessionDTO = wechatAdapter.jscode2session(loginDTO.getCode());

            List<User> users = userDao.findUserByOpenId(sessionDTO.getOpenid());
            String token = UUID.randomUUID().toString();
            if (CollectionUtils.isEmpty(users)) {
                user = new User();
                user.setToken(token);
                user.setOpenId(sessionDTO.getOpenid());
                integer=userDao.addUser(user);
                num=1;
            }else {
                user=users.get(0);
                if(!StringUtils.isEmpty(user.getToken())){
                    value= stringRedisTemplate.opsForValue().get(user.getToken());
                    if(StringUtils.isEmpty(value)){
                        user.setToken(token);
                        num=1;
                    }else {
                        user.setToken(user.getToken());
                        num=2;
                    }
                }else {
                    user.setToken(token);
                    num=1;
                }
                user.setOpenId(sessionDTO.getOpenid());
                integer=userDao.updateUser(user);

            }
            if (integer == 1) {
                String tpstring=JsonUtils.objectToJson(user);
                if(num==1){
                    stringRedisTemplate.opsForValue().set(token,tpstring,2, TimeUnit.DAYS);
                }else if(num==2){
                    stringRedisTemplate.opsForValue().set(user.getToken(),tpstring,2, TimeUnit.DAYS);
                    token=user.getToken();
                }

                return ResultDTO.ok(token);
            } else {
                return ResultDTO.fail(CommonErrorCode.UNKOWN_ERROR);
            }
        } catch (ErrorCodeException e) {
            log.error("login error, info : {}", loginDTO, e.getMessage());
            return ResultDTO.fail(e);
        } catch (Exception e) {
            log.error("login error, info : {}", loginDTO, e);
            return ResultDTO.fail(CommonErrorCode.UNKOWN_ERROR);
        }
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
