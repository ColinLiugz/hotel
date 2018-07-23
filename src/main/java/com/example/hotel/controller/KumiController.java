package com.example.hotel.controller;

import com.example.hotel.Util.KumiUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("")
public class KumiController {

    //暴露出来给前端调用的路由
    @RequestMapping(value = "/getUserInfo")
    public String getUserInfo(HttpServletRequest request){
        String url = "http://saas.top/kumi-web-boss/event/user/userInfo";
        Map<String,Object> params = new HashMap<>();
        String result = KumiUtil.sendPost(url,params,request);
        return result;
    }

    //演示用后台登录
    @RequestMapping(value = "/login")
    public String login(String phoneNum, String password,HttpServletRequest request){
        Map<String,Object> params = new HashMap<>();
        Enumeration em = request.getParameterNames();
        while (em.hasMoreElements()) {
            String name = (String) em.nextElement();
            String value = request.getParameter(name);
            params.put(name,value);
        }
        String url = "http://saas.vip/kumi-web-portal/sys/login/v2";
        String result = KumiUtil.sendPost(url,params,request);
        return result;
    }

}
