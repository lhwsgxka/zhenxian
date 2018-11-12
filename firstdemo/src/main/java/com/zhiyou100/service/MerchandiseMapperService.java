package com.zhiyou100.service;

import com.github.pagehelper.PageHelper;
import com.zhiyou100.dao.MerchandiseMapper;
import com.zhiyou100.pojo.Merchandise;
import com.zhiyou100.pojo.User;
import com.zhiyou100.pojo.UserMerchandise;
import com.zhiyou100.respon.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MerchandiseMapperService {
    @Autowired
    MerchandiseMapper merchandiseMapper;
    @Autowired
    UserMerchandiseService userMerchandiseService;
    @Autowired
    UserMapperService userMapperService;

    //根据状态查询 如 打折 一般 会员 拼团
    public List<Merchandise> selectByPrimaryListState(int page, int state) {
        PageHelper.startPage(page, 5);
        return merchandiseMapper.selectByPrimaryListState(state);
    }

    //根据商品的类型查询
    public List<Merchandise> selectByPrimaryListType(int page, int type) {
        PageHelper.startPage(page, 5);
        return merchandiseMapper.selectByPrimaryListType(type);
    }

    public Merchandise selectByPrimaryKey(int id) {
        return merchandiseMapper.selectByPrimaryKey(id);
    }

    //拼团商品购买  事务处理 购物表添加数据 用户余额扣钱
    @Transactional
    public String groupBooking(User user, Merchandise merchandise, String site) {
        //创造订单
        UserMerchandise userMerchandise = new UserMerchandise();
        userMerchandise.setMid(merchandise.getId());
        userMerchandise.setSite(site);
        userMerchandise.setUid(user.getId());
        //用户余额扣钱
        user.setMoney(user.getMoney() - merchandise.getPrice());
        userMapperService.updateByPrimaryKeySelective(user);
        return Response.responseSucceed(200, "购买成功");
    }

    @Transactional
    public void purchase(List<Merchandise> merchandises, User user, String size) {
        for (Merchandise merchandise :
                merchandises) {
            //创造订单
            UserMerchandise userMerchandise = new UserMerchandise();
            userMerchandise.setSite(size);
            userMerchandise.setUid(user.getId());
            userMerchandise.setMid(merchandise.getId());
            //用户余额扣钱
            user.setMoney(user.getMoney() - merchandise.getPrice());
            userMapperService.updateByPrimaryKeySelective(user);
        }

    }
}