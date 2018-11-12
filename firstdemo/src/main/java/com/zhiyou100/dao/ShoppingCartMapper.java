package com.zhiyou100.dao;

import com.zhiyou100.pojo.Merchandise;
import com.zhiyou100.pojo.ShoppingCart;

import java.util.List;

public interface ShoppingCartMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ShoppingCart record);

    int insertSelective(ShoppingCart record);

    ShoppingCart selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ShoppingCart record);

    int updateByPrimaryKey(ShoppingCart record);

    List<Merchandise> selectByUid(int uid);
}