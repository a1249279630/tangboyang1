package com.example.tangboyang1.dao;

import com.example.tangboyang1.mapper.UserMapper;
import com.example.tangboyang1.pojo.User;
import com.example.tangboyang1.pojo.UserExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDao {
    @Autowired
    private UserMapper userMapper;

    public List<User> findAllUser(){
        UserExample userExample=new UserExample();
        return userMapper.selectByExample(userExample);
    }
    /*通过激活码修改状态然后返回用户*/
//    public Integer updateState(User user, String activeCode)  {
//        UserExample userExample=new UserExample();
//        userExample.createCriteria().andActivecodeEqualTo(activeCode);
//        user.setState(1);
//        return (Integer) userMapper.updateByExampleSelective(user,userExample);
//    }

    public List<User> findUserByActiveCode(String activeCode){
        UserExample userExample=new UserExample();
        userExample.createCriteria().andActivecodeEqualTo(activeCode);
        return  userMapper.selectByExample(userExample);
    }

    public User findUserById(int id){
        return userMapper.selectByPrimaryKey(id);
    }

    public User findUserByUserNameandPassword(@Param("username") String username,@Param("password") String password){
        UserExample userExample = new UserExample();
        return userMapper.loginUser(username,password);
    }
    public User findUserByUserNameandPassword111(String username,String password){
        UserExample userExample = new UserExample();
        return userMapper.loginUser(username,password);
    }
    public List<User> findUserByUserName(String username){
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo(username);
        return userMapper.selectByExample(userExample);
    }

    public Integer addUser(User user){
        return userMapper.insert(user);
    }

    public Integer deleteUser(int id){
        return userMapper.deleteByPrimaryKey(id);
    }
    public Integer updateUser(User user){
        return userMapper.updateByPrimaryKey(user);
    }

    public Integer updateUserbyUsername(User user, String username){
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo(username);
        return userMapper.updateByExampleSelective(user,userExample);
    }

    public List<User> findUserByOpenId(String openid){
        UserExample userExample=new UserExample();
        userExample.createCriteria().andOpenIdEqualTo(openid);
        return userMapper.selectByExample(userExample);
    }
    public Integer updateState(User user, String activeCode) {
        return null;
    }

    public List<User> FindUserByToken(String token) {
        UserExample userExample=new UserExample();
        userExample.createCriteria().andTokenEqualTo(token);
        return userMapper.selectByExample(userExample);
    }
}
