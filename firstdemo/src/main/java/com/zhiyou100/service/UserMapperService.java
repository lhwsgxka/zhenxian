package com.zhiyou100.service;

import com.zhiyou100.dao.UserMapper;
import com.zhiyou100.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserMapperService {
    @Autowired
    UserMapper userMapper;
    //自定义查询，通过用户名和密码查询是否存在该对象
    public User selectByUser(String name,String password){
        return userMapper.selectByUser(name,password);
    }

    public int insertSelective(User user){
        return userMapper.insertSelective(user);
    }

    public int updateByPrimaryKeySelective(User user){
        return userMapper.updateByPrimaryKeySelective(user);
    }

}
