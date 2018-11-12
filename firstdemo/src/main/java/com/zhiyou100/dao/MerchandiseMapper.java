package com.zhiyou100.dao;

import com.zhiyou100.pojo.Merchandise;

import java.util.List;

public interface MerchandiseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Merchandise record);

    int insertSelective(Merchandise record);

    Merchandise selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Merchandise record);

    int updateByPrimaryKey(Merchandise record);

    List<Merchandise> selectByPrimaryListState(int state);
    List<Merchandise> selectByPrimaryListType(int type);
}