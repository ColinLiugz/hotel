package com.example.hotel.controller;

import com.example.hotel.Util.ApiResult;
import com.example.hotel.Util.MapUtil;
import com.example.hotel.entity.Housing;
import com.example.hotel.entity.Orders;
import com.example.hotel.entity.User;
import com.example.hotel.repository.HousingRepository;
import com.example.hotel.repository.OrdersRepository;
import com.example.hotel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@CrossOrigin
public class OrdersController {
    @Autowired
    private OrdersRepository ordersRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private HousingRepository housingRepository;

    @RequestMapping("/order/add")
    public ApiResult add(String userId, String housingId, String peopleNumber, String stayInTime, String checkOutTime){

        if(StringUtils.isEmpty(userId) || StringUtils.isEmpty(housingId) || StringUtils.isEmpty(peopleNumber) || StringUtils.isEmpty(stayInTime) || StringUtils.isEmpty(checkOutTime)){
            return  ApiResult.error("数据不能为空");
        }
        User user = userRepository.getOneByKeyId(Integer.parseInt(userId));
        if(null == user){
            return ApiResult.error("用户不存在！");
        }
        Housing housing = housingRepository.getOneByKeyId(Integer.parseInt(housingId));
        if(null == housing){
            return ApiResult.error("房源不存在！");
        }
        if(0 == Integer.parseInt(peopleNumber)){
            return ApiResult.error("入住人数不能为0！");
        }
        if(Integer.parseInt(peopleNumber) > housing.getCanLiveInNumber()){
            return ApiResult.error("入住人数超过限制！");
        }
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date stayInTimeDate = new Date();
        Date checkOutTimeDate = new Date();
        try {
            stayInTimeDate = format.parse(stayInTime);
            checkOutTimeDate = format.parse(stayInTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(stayInTimeDate.getTime()<housing.getAvailableStartTime().getTime() || checkOutTimeDate.getTime()>housing.getAvailableEndTime().getTime()){
            return ApiResult.error("入住时间不超出限制！");
        }
        Orders order =new Orders();
        order.setUserId(Integer.parseInt(userId));
        order.setHousingId(Integer.parseInt(housingId));
        order.setPeopleNumber(Integer.parseInt(peopleNumber));
        order.setStayInTime(stayInTimeDate);
        order.setCheckOutTime(checkOutTimeDate);
        order.setHasPayMoney(0);
        order.setCreateTimeDate(new Date());
        ordersRepository.save(order);
        return ApiResult.ok("下单成功");
    }

    @RequestMapping("/order/listMyReserved")
    public  ApiResult listMyReserved(String userKeyId){
        if(StringUtils.isEmpty(userKeyId)){
            return ApiResult.error("数据不能为空！");
        }
        User user = userRepository.getOneByKeyId(Integer.parseInt(userKeyId));
        if(null == user) {
            return ApiResult.error("该用户不存在！");
        }
        List<Orders> ordersList = ordersRepository.listMyReserved(user.getKeyId());
        List<Map<String,Object>> resultMapList = new ArrayList<>();
        for(Orders orders : ordersList){
            HashMap<String,Object> orderMap = MapUtil.beanToMap(orders);
            Housing housing = housingRepository.getOneByKeyId(orders.getHousingId());
            HashMap<String,Object> housingMap = MapUtil.beanToMap(housing);
            housingMap.remove("keyId");
            housingMap.remove("isDeleted");
            housingMap.remove("createTimeDate");
            housingMap.remove("latestTime");
            User user1 = userRepository.getOneByKeyId(housing.getUserId());
            HashMap<String,Object> userMap = MapUtil.beanToMap(user1);
            userMap.remove("keyId");
            userMap.remove("isDeleted");
            userMap.remove("createTimeDate");
            userMap.remove("latestTime");
            orderMap.putAll(housingMap);
            orderMap.putAll(userMap);
            resultMapList.add(orderMap);
        }
        return ApiResult.ok(resultMapList);
    }

    @RequestMapping("/order/listMyPublished")
    public  ApiResult listMyPublished(String userKeyId) {
        if (StringUtils.isEmpty(userKeyId)) {
            return ApiResult.error("数据不能为空！");
        }
        User user = userRepository.getOneByKeyId(Integer.parseInt(userKeyId));
        if (null == user) {
            return ApiResult.error("该用户不存在！");
        }
        List<Orders> ordersList = ordersRepository.listMyPublished(user.getKeyId());
        List<Map<String, Object>> resultMapList = new ArrayList<>();
        for (Orders order : ordersList) {
            HashMap<String, Object> orderMap = MapUtil.beanToMap(order);
            Housing housing = housingRepository.getOneByKeyId(order.getHousingId());
            User user1 = userRepository.getOneByKeyId(order.getUserId());
            HashMap<String, Object> userMap = MapUtil.beanToMap(user1);
            userMap.remove("keyId");
            userMap.remove("isDeleted");
            userMap.remove("createTimeDate");
            userMap.remove("latestTime");
            HashMap<String, Object> housingMap = MapUtil.beanToMap(housing);
            housingMap.remove("keyId");
            housingMap.remove("isDeleted");
            housingMap.remove("createTimeDate");
            housingMap.remove("latestTime");
            orderMap.putAll(housingMap);
            orderMap.putAll(userMap);
            resultMapList.add(orderMap);
        }
        return ApiResult.ok(resultMapList);
    }

}
