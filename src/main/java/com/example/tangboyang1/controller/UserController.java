package com.example.tangboyang1.controller;//package com.example.tangboyang.controller;
import com.example.tangboyang1.dto.*;
//import com.example.tangboyang1.dto.TokenDTO;
import com.example.tangboyang1.pojo.User;
import com.example.tangboyang1.adapter.WechatAdapter;
import com.example.tangboyang1.error.CommonErrorCode;
import com.example.tangboyang1.error.ErrorCodeException;
import com.example.tangboyang1.request.StoreRequest.AddStoreRequest;
import com.example.tangboyang1.request.UserRequest.*;
import com.example.tangboyang1.response.FindAllUserResponse;
import com.example.tangboyang1.service.StoreService;
import com.example.tangboyang1.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("UserController")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private StoreService storeService;
    @Autowired
    private WechatAdapter wechatAdapter;


    @GetMapping(value = "find/all/user")
    public List<FindAllUserResponse> findAllUser(Integer pageNumber, Integer pageSize) {

        return userService.findAllUser(pageNumber, pageSize);
    }

    //    @GetMapping(value = "update/userState/by/id")
//    public String findUserByActiveCode(HttpServletRequest request,@RequestBody String activeCode){
//        return userService.UpdateUserStateByActiveCode(request,activeCode);
//    }
    @PostMapping(value = "Regiest/user/by/json")
    public String RegiestUserByJson(@RequestBody RegistUserRequest registUserRequest) {
        Integer integer = userService.addUser(registUserRequest);
        if (integer == 1) {
            return "注册成功";
        } else {
            return "注册失败，用户名重复";
        }
    }

    @DeleteMapping(value = "delete/user/by/id")
    public String deleteUser(@RequestBody Integer id) {
        Integer integer = userService.deleteUser(id);
        if (integer == 1) {
            return "删除成功";
        } else {
            return "删除失败，该用户名下有订单";
        }
    }

    @GetMapping(value = "fing/user/by/usernameAndpassward")
    public String findUserByUserNameandPassword(HttpServletRequest request, String username, String passward) {
        return userService.findUserByUserNameandPassword(request, username, passward);

    }

    @PostMapping(value = "update/psssward/by/username")
    public String UpdateUserPassword( @RequestBody UpdateUserPasswardRequest updateUserPasswardRequest) {
        Integer integer = userService.updateUserPassword(updateUserPasswardRequest);
        if (integer == 1) {
            return "密码更改成功！";
        } else if (integer == 0) {
            return "两次输入的密码不一致";
        } else if (integer == -1) {
            return "新密码不能与旧密码一样";
        } else if (integer == -2) {
            return "安全码错误，请输入正确的安全码！";
        } else return "服务器错误";
    }

    @PostMapping(value = "update/user/by/id")
    public Integer updateUser(@RequestBody UpdateUserRequest updateUserRequest) {
        return userService.updateUser(updateUserRequest);
    }

    @PostMapping(value = "update/userRole/by/id")
    public String updateUserRole(String userrole, Integer id) {

        if (userrole.equals("普通用户") || userrole.equals("卖家用户")) {
            Integer integer = userService.updateUserRole(userrole, id);
            if (integer == 1) {
                return "跟新成功";
            } else {
                return "跟新失败，id不存在";
            }
        } else {
            return "用户角色只能为‘普通用户’或‘卖家用户’";
        }

    }


    @GetMapping(value = "Regiest/Store/by/user")
    public void RegistStore(AddStoreRequest addStoreRequest) {
        storeService.RegistStore(addStoreRequest);
    }

    @GetMapping(value = "fing/user/by/openid")
    public ResultDTO findUserByOpenid(LoginDTO loginDTO) {
        return userService.findUserByOpenid(loginDTO);
    }

}
