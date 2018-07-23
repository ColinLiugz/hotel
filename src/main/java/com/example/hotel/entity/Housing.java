package com.example.hotel.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
@Entity
public class Housing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer keyId;//id
    private Integer isDeleted = 0;//是否已删除
    private Date createTimeDate;//创建时间
    private Date latestTime;//最后修改时间
    private Integer userId;//房主id
    private Double price;//价格
    private String city;//所属城市
    private String address;//具体地址
    private String housingType;//房源类型
    private String roomType;//房间类型
    private Integer canLiveInNumber;//可住人数
    private Date availableStartTime;//可用时间（开始）
    private Date availableEndTime;//可用时间（截止）

    public Integer getKeyId() {
        return keyId;
    }

    public void setKeyId(Integer keyId) {
        this.keyId = keyId;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Date getCreateTimeDate() {
        return createTimeDate;
    }

    public void setCreateTimeDate(Date createTimeDate) {
        this.createTimeDate = createTimeDate;
    }

    public Date getLatestTime() {
        return latestTime;
    }

    public void setLatestTime(Date latestTime) {
        this.latestTime = latestTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHousingType() {
        return housingType;
    }

    public void setHousingType(String housingType) {
        this.housingType = housingType;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public Integer getCanLiveInNumber() {
        return canLiveInNumber;
    }

    public void setCanLiveInNumber(Integer canLiveInNumber) {
        this.canLiveInNumber = canLiveInNumber;
    }

    public Date getAvailableStartTime() {
        return availableStartTime;
    }

    public void setAvailableStartTime(Date availableStartTime) {
        this.availableStartTime = availableStartTime;
    }

    public Date getAvailableEndTime() {
        return availableEndTime;
    }

    public void setAvailableEndTime(Date availableEndTime) {
        this.availableEndTime = availableEndTime;
    }
}
