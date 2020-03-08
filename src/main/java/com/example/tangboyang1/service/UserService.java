package com.example.tangboyang1.service;

import com.example.tangboyang1.pojo.User;
import com.example.tangboyang1.request.UserRequest.RegistUserRequest;
//import com.example.tangboyang1.request.UserRequest.UpdateUserPasswardRequest;
//import com.example.tangboyang1.request.UserRequest.UpdateUserRequest;
import com.example.tangboyang1.request.UserRequest.UpdateUserPasswardRequest;
import com.example.tangboyang1.request.UserRequest.UpdateUserRequest;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public interface UserService {

    Integer addUser(RegistUserRequest registUserRequest);

    Integer deleteUser(Integer id);

    Integer updateUser(HttpServletRequest request,UpdateUserRequest updateUserRequest, Integer id);

    List<User> findAllUser(Integer pageNumber, Integer pageSize);

    Integer updateState(String activeCode);
//
    User findUserById(int id);
//
    String findUserByUserNameandPassword(HttpServletRequest request, String userName, String password);

    List<User> findUserByActiveCode(String activeCode);

    Integer updateUserPassword(HttpServletRequest request,UpdateUserPasswardRequest updateUserPasswardRequest);

    Integer updateUserRole(String userrole, Integer id);

    User findUserByOpenid(String openid);

    Integer saveOrUpdate(User user);

    User FindUserByToken(String token);

//    String findUserByUserNameandPassword(LoginUserRequest loginUser);
}
