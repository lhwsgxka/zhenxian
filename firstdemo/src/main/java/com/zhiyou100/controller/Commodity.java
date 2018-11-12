package com.zhiyou100.controller;

import com.google.gson.Gson;
import com.zhiyou100.pojo.Merchandise;
import com.zhiyou100.respon.Response;
import com.zhiyou100.service.MerchandiseMapperService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Slf4j
@RequestMapping("")
@Controller
public class Commodity {
    @Autowired
    MerchandiseMapperService merchandiseMapperService;

    /***
     * 查询商品展示指定状态的 指定页数的商品信息
     * @param page
     * @return
     */
    @ResponseBody
    @RequestMapping("")
    public String ListCommodityState(int page, int state) {
        List<Merchandise> merchandises =
                merchandiseMapperService.selectByPrimaryListState(page, state);
        String s = new Gson().toJson(merchandises);
        return Response.responseSucceed(200, s);
    }

    //通过商品类型获取
    @ResponseBody
    @RequestMapping("")
    public String ListCommodityType(int page, int type) {
        List<Merchandise> merchandises =
                merchandiseMapperService.selectByPrimaryListType(page, type);
        String s = new Gson().toJson(merchandises);
        return Response.responseSucceed(200, s);
    }

    //通过id获取商品详情
    @ResponseBody
    @RequestMapping()
    public String CommodityId(int id) {
        Merchandise merchandise = merchandiseMapperService.selectByPrimaryKey(id);
        String toJson = new Gson().toJson(merchandise);
        return Response.responseSucceed(200, toJson);
    }


}
