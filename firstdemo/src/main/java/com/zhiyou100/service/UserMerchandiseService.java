package com.zhiyou100.service;


import com.zhiyou100.dao.UserMerchandiseMapper;
import com.zhiyou100.pojo.UserMerchandise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//订单表
@Service

public class UserMerchandiseService {
    @Autowired
    UserMerchandiseMapper userMerchandiseMapper;
    //添加订单
    public int insertSelective(UserMerchandise userMerchandise){
        return userMerchandiseMapper.insertSelective(userMerchandise);
    }
}
