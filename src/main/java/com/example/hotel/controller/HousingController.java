package com.example.hotel.controller;

import com.example.hotel.Util.ApiResult;
import com.example.hotel.entity.Housing;
import com.example.hotel.entity.User;
import com.example.hotel.repository.HousingRepository;
import com.example.hotel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
public class HousingController {
    @Autowired
    private HousingRepository housingRepository;
    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/housing/add")
    public ApiResult addHousing(String userKeyId, String price, String city, String address, String  housingType, String roomType, String canLiveInNumber, String availableStartTime,String availableEndTime){
        if(StringUtils.isEmpty(userKeyId) || StringUtils.isEmpty(price) || StringUtils.isEmpty(city) || StringUtils.isEmpty(address) || StringUtils.isEmpty(housingType) ||
                StringUtils.isEmpty(roomType) || StringUtils.isEmpty(canLiveInNumber) || StringUtils.isEmpty(availableStartTime) || StringUtils.isEmpty(availableEndTime)){
            return ApiResult.error("数据不能为空！");
        }
        Housing housing = new Housing();
        housing.setUserId(Integer.parseInt(userKeyId));
        housing.setCreateTimeDate(new Date());
        housing.setPrice(Double.parseDouble(price));
        housing.setCity(city);
        housing.setAddress(address);
        housing.setHousingType(housingType);
        housing.setRoomType(roomType);
        housing.setCanLiveInNumber(Integer.parseInt(canLiveInNumber));
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            housing.setAvailableStartTime(format.parse(availableStartTime));
            housing.setAvailableEndTime(format.parse(availableEndTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        housingRepository.save(housing);
        return ApiResult.ok("保存成功！");
    }

    @RequestMapping("/housing/list")
    public ApiResult listHousing(String city,String liveInTime){
        List<Housing> housingList;
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date liveInTimeDate = new Date();
        try {
            liveInTimeDate = format.parse(liveInTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(StringUtils.isEmpty(city) && StringUtils.isEmpty(liveInTime)) {
            housingList = housingRepository.listAll();
        }else if (!StringUtils.isEmpty(city) && StringUtils.isEmpty(liveInTime)){
            housingList = housingRepository.listByCity(city);
        }else if(StringUtils.isEmpty(city) && !StringUtils.isEmpty(liveInTime)){
            housingList = housingRepository.listByTime(liveInTimeDate,liveInTimeDate);
        }else {
            housingList = housingRepository.listByCityAndTime(city,liveInTimeDate,liveInTimeDate);
        }
        return ApiResult.ok(housingList);
    }

    @RequestMapping("/housing/delete")
    public ApiResult delete(String housingKeyId){
        if(StringUtils.isEmpty(housingKeyId)){
            return ApiResult.error("数据不能为空");
        }
        Housing housing = housingRepository.getOneByKeyId(Integer.parseInt(housingKeyId));
        if(null == housing){
            return ApiResult.error("该房间不存在！");
        }
        housing.setIsDeleted(1);
        housingRepository.save(housing);
        return ApiResult.ok("删除成功！");
    }

    @RequestMapping("/housing/update")
    public ApiResult updateHousing(String housingKeyId, String price, String city, String address, String  housingType, String roomType, String canLiveInNumber, String availableStartTime,String availableEndTime){
        if(StringUtils.isEmpty(housingKeyId) || StringUtils.isEmpty(price) || StringUtils.isEmpty(city) || StringUtils.isEmpty(address) || StringUtils.isEmpty(housingType) ||
                StringUtils.isEmpty(roomType) || StringUtils.isEmpty(canLiveInNumber) || StringUtils.isEmpty(availableStartTime) || StringUtils.isEmpty(availableEndTime)){
            return ApiResult.error("数据不能为空！");
        }
        Housing housing = housingRepository.getOneByKeyId(Integer.parseInt(housingKeyId));
        if(null == housing){
            return ApiResult.error("该房间不存在");
        }
        housing.setCreateTimeDate(new Date());
        housing.setPrice(Double.parseDouble(price));
        housing.setCity(city);
        housing.setAddress(address);
        housing.setHousingType(housingType);
        housing.setRoomType(roomType);
        housing.setCanLiveInNumber(Integer.parseInt(canLiveInNumber));
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            housing.setAvailableStartTime(format.parse(availableStartTime));
            housing.setAvailableEndTime(format.parse(availableEndTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        housingRepository.save(housing);
        return ApiResult.ok("修改成功！");
    }

    @RequestMapping("/housing/findOne")
    public ApiResult findOne(String housingKeyId){
        if(StringUtils.isEmpty(housingKeyId)){
            return ApiResult.error("数据不能为空");
        }
        Housing housing = housingRepository.getOneByKeyId(Integer.parseInt(housingKeyId));
        if(null == housing){
            return ApiResult.error("该房间不存在！");
        }
        return ApiResult.ok(housing);
    }

    @RequestMapping("/housing/listMyPublished")
    public ApiResult listMyPublished(String userKeyId){
        if (null == userKeyId){
            return ApiResult.error("数据不能为空");
        }
        User user = userRepository.getOneByKeyId(Integer.parseInt(userKeyId));
        if(null == user){
            return  ApiResult.error("该用户不存在");
        }
        List<Housing> housingList = housingRepository.listMyPublished(Integer.parseInt(userKeyId));
        return ApiResult.ok(housingList);
    }

}
