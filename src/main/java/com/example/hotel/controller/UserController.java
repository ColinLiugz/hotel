package com.example.hotel.controller;

import com.example.hotel.Util.ApiResult;
import com.example.hotel.entity.User;
import com.example.hotel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/user/register2")
    public ApiResult getAllStudent2(){
        List<User> users = userRepository.findAll();
        return ApiResult.ok(users);
    }

    @RequestMapping("/user/register3")
    public ApiResult getAllStudent3(){
        List<User> users = userRepository.findAll();
        return ApiResult.ok(users.toString());
    }

    @RequestMapping("/user/register")
    public ApiResult getAllStudent(String name,String phoneNumber,String idCard,String password){
        if(StringUtils.isEmpty(name) || StringUtils.isEmpty(phoneNumber) || StringUtils.isEmpty(idCard) || StringUtils.isEmpty(password))
        {
            return ApiResult.error("数据不能为空！");
        }
        if(null != userRepository.getByPhoneNumber(phoneNumber) ){
            return ApiResult.error("该手机号已经被注册！");
        }
        User user = new User();
        user.setUserName(name);
        user.setPhoneNumber(phoneNumber);
        user.setIdCard(idCard);
        user.setPassword(password);
        user.setCreateTimeDate(new Date());
        userRepository.save(user);
        return ApiResult.ok("注册成功！");
    }

    @RequestMapping("/user/login")
    public ApiResult login(String phoneNumber,String password){
        if(StringUtils.isEmpty(phoneNumber) || StringUtils.isEmpty(password)){
            return ApiResult.error("数据不能为空 ！");
        }
        User user = userRepository.getByPhoneNumber(phoneNumber);
        if(null == user){
            return ApiResult.error("账号不存在！");
        }else if(!password.equals(user.getPassword()) ){
            return ApiResult.error("密码错误！");
        }
        return ApiResult.ok(user);
    }

    @RequestMapping("/user/findOne")
    public ApiResult  findOne(String userKeyId){
        if(StringUtils.isEmpty(userKeyId)){
            return ApiResult.error("数据不能为空！");
        }
        User user = userRepository.getOneByKeyId(Integer.parseInt(userKeyId));
        if(null == user){
            return ApiResult.error("没有该用户！");
        }
        return ApiResult.ok(user);
    }

    @RequestMapping("/user/update")
    public ApiResult update(String userKeyId,String name,String phoneNumber,String idCard,String password){
        if(StringUtils.isEmpty(userKeyId) || StringUtils.isEmpty(name) || StringUtils.isEmpty(phoneNumber) || StringUtils.isEmpty(idCard) || StringUtils.isEmpty(password))
        {
            return ApiResult.error("数据不能为空！");
        }
        User user  = userRepository.getOneByKeyId(Integer.parseInt(userKeyId));
        if(null == user){
            return ApiResult.error("不存在的用户！");
        }
        user.setUserName(name);
        user.setPhoneNumber(phoneNumber);
        user.setIdCard(idCard);
        user.setPassword(password);
        user.setLatestTime(new Date());
        userRepository.save(user);
        return ApiResult.ok("修改成功！");
    }



    @RequestMapping("/testList")
    public ApiResult  testList(){
        List<Integer> list = new ArrayList<>();
        for(int i=0;i<10;i++){
            list.add(i);
        }
        for(int i=0;i<list.size()-1;i++){
//            System.out.println("i 前"+i);
//            System.out.println("i 为"+list.get(i));
            if(list.get(i)%2 == 0){
                list.remove(i);
//                System.out.println("size 为"+list.size());
                i--;
//                System.out.println("i 后"+i);
            }
        }
//        System.out.print("list : "+list.toString());
        return ApiResult.ok();
    }
}
