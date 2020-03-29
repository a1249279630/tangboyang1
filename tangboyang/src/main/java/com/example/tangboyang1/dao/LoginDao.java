package com.example.tangboyang1.dao;

import com.example.tangboyang1.mapper.UserMapper;
import com.example.tangboyang1.pojo.User;
import com.example.tangboyang1.pojo.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Repository
public class LoginDao {
    @Autowired
    private UserMapper userMapper;
    public User FindUserByToken(String token) {
        UserExample userExample=new UserExample();
        userExample.createCriteria().andTokenEqualTo(token);

        List<User> users = userMapper.selectByExample(userExample);
        if(CollectionUtils.isEmpty(users)){
            return null;
        }

        return users.get(0);
    }
}
