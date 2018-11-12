package com.zhiyou100.service;

import com.zhiyou100.dao.ShoppingCartMapper;
import com.zhiyou100.pojo.Merchandise;
import com.zhiyou100.pojo.ShoppingCart;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//购物车
@Slf4j
@Service
public class ShoppingCartService {
    @Autowired
    ShoppingCartMapper shoppingCartMapper;

    public int insertSelective(ShoppingCart shoppingCart) {
        return shoppingCartMapper.insertSelective(shoppingCart);
    }

    //通过用户id查询得到商品
    public List<Merchandise> selectByUid(int uid) {
        return shoppingCartMapper.selectByUid(uid);
    }
}
