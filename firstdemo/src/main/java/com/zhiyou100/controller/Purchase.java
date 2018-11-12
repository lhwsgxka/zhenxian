package com.zhiyou100.controller;

import com.google.gson.Gson;
import com.zhiyou100.pojo.Merchandise;
import com.zhiyou100.pojo.ShoppingCart;
import com.zhiyou100.pojo.User;
import com.zhiyou100.respon.Response;
import com.zhiyou100.service.MerchandiseMapperService;
import com.zhiyou100.service.ShoppingCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/***
 * 商品购买后台
 */
@RequestMapping
@Slf4j
@Controller
public class Purchase {
    /***
     * 拼团的商品
     * 购物车不需要添加
     */
    @Autowired
    MerchandiseMapperService merchandiseMapperService;
    @Autowired
    ShoppingCartService shoppingCartService;
    @ResponseBody
    @RequestMapping()
    public String groupBooking(HttpServletRequest req,int mid,String site) {
        //对商品的购买 由于是直接购买
        //首先是判断余额是否满足购买
        User user = (User) req.getSession().getAttribute("login");
        Double money = user.getMoney();
        //获取商品
        Merchandise merchandise = merchandiseMapperService.selectByPrimaryKey(mid);
        if (merchandise.getPrice()>money){
            return Response.responseError(300,"余额不足无法购买");
        }else {
            //满足的话，事务处理 购物表添加数据 用户余额扣钱
            String success = merchandiseMapperService.groupBooking(user, merchandise, site);
            return success;
        }
    }
    //商品购物车的添加
    @RequestMapping("")
    @ResponseBody
    public String shoppingCart(HttpServletRequest req,int mid){
        User user = (User) req.getSession().getAttribute("login");
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setMid(mid);
        shoppingCart.setUid(user.getId());
        //保存到数据库
        shoppingCartService.insertSelective(shoppingCart);
        String toJson = new Gson().toJson(shoppingCart);
        return Response.responseSucceed(200,toJson);
    }
    @RequestMapping("")
    @ResponseBody
    //正常的商品购买
    public String purchase(HttpServletRequest req,String size){
        //同上面 首先计算商品 的价钱
        User user = (User) req.getSession().getAttribute("login");
        Integer uid = user.getId();
        List<Merchandise> merchandises = shoppingCartService.selectByUid(uid);
        double merchandiseMoney=0;
        for (Merchandise merchandise:
             merchandises) {
            merchandiseMoney=merchandise.getLimit()+merchandiseMoney;
        }
        //判断用户余额是否可以购买
        if (user.getMoney()<merchandiseMoney){
            //无法购买
            return Response.responseError(300,"余额不足，请充值");
        }else {
            //完成商品购买
            merchandiseMapperService.purchase(merchandises,user,size);
            return Response.responseSucceed(200,"购买成功");
        }
    }



}

