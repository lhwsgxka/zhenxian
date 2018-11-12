package com.zhiyou100.dao;

import com.zhiyou100.pojo.UserMerchandise;

public interface UserMerchandiseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserMerchandise record);

    int insertSelective(UserMerchandise record);

    UserMerchandise selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserMerchandise record);

    int updateByPrimaryKey(UserMerchandise record);
}