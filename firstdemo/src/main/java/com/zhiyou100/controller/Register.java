package com.zhiyou100.controller;

import com.aliyuncs.exceptions.ClientException;
import com.zhiyou100.pojo.User;
import com.zhiyou100.respon.Response;
import com.zhiyou100.service.UserMapperService;
import com.zhiyou100.util.MailUtil;
import com.zhiyou100.util.SMSUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Random;

/***
 *
 * 用来注册，首先用户通过邮箱或手机号注册获取验证码（之前需要校验，保证账户的唯一性）
 * 保存在ssion中，然后校验，填写用户名和密码，校验保证用户唯一，成功后保存到数据库中
 */
@Controller
@Slf4j
@RequestMapping()
public class Register {
    /***
     * 获取验证吗
     */
    @Autowired
    UserMapperService userMapperService;

    @ResponseBody
    @RequestMapping("/code.do")
    public String code(HttpServletRequest req, String mail, String phone) {
        //随机生成验证码
        Random random = new Random();
        String code = "";
        for (int i = 0; i < 6; i++) {
            code += random.nextInt(10);
        }
        System.out.println(code);
        //验证码保存到session
        req.getSession().setAttribute("code", code);
        //验证邮箱或手机号是否被注册

        //发送验证码
        if (StringUtils.isBlank(mail)) {
            if (StringUtils.isBlank(phone) || phone.length() != 11) {
                return Response.responseError(300,"邮箱或密码不合法");
            } else {
                //通过手机号发送验证码
                try {
                    SMSUtil.sendSms(phone, code);
                    return Response.responseSucceed(200,"success");
                } catch (ClientException e) {
                    e.printStackTrace();
                    return Response.responseError(500,"服务器错误");
                }
            }
        } else {
            //调用邮箱发送
            MailUtil.sendMail(mail, code);
            return Response.responseSucceed(200,"success");
        }
    }

    /***
     *
     * 获取验证吗校验，完成注册
     */
    @ResponseBody
    public String Register(String code, String userName, String password, HttpServletRequest req) {

        //首先是验证码的校验
        String code1 = (String) req.getSession().getAttribute("code");
        if (code1 != code) {

            return Response.responseError(300,"验证码错误");
        } else {
            //对账户，校验是否存在

            //将用户信息写入数据库
            User user = new User();
            user.setPassword(password);
            user.setUserName(userName);
            userMapperService.insertSelective(user);
            return Response.responseSucceed(200,"success");
        }

    }
}