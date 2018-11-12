package com.zhiyou100.controller;

import com.zhiyou100.pojo.User;
import com.zhiyou100.respon.Response;
import com.zhiyou100.service.UserMapperService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequestMapping("/login")
public class Login {
    @Autowired
    UserMapperService userMapperService;
    @ResponseBody
    @RequestMapping("/login.do")
    public String login(HttpServletRequest req, String userName, String password) {
        HttpSession session = req.getSession();
        if (StringUtils.isBlank(userName) || StringUtils.isBlank(password)) {

            return Response.responseError(300,"账号或密码为空");

        } else {
            //通过用户的用户名和密码去数据库查询是否存在
            User user = userMapperService.selectByUser(userName, password);
            if (user != null) {
                //登录成功，保存session中，并且返回成功到前端
                session.setAttribute("login", user);
                return Response.responseSucceed(200,"success");
            } else {
                //登录失败，告诉错误信息，
                return Response.responseError(300,"账号或密码错误，请重新登录");
            }
        }

    }


}
