package com.example.hotel.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer keyId;//id
    private Integer isDeleted = 0;//是否已删除
    private Date createTimeDate;//创建时间
    private Date latestTime;//最后修改时间
    private Integer userId;//用户id
    private Integer housingId;//房源id
    private Integer peopleNumber;//入住人数
    private Integer hasPayMoney;//是否交钱
    private Date stayInTime;//入住日期
    private Date checkOutTime;//退房日期

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

    public Integer getHousingId() {
        return housingId;
    }

    public void setHousingId(Integer housingId) {
        this.housingId = housingId;
    }

    public Integer getPeopleNumber() {
        return peopleNumber;
    }

    public void setPeopleNumber(Integer peopleNumber) {
        this.peopleNumber = peopleNumber;
    }

    public Integer getHasPayMoney() {
        return hasPayMoney;
    }

    public void setHasPayMoney(Integer hasPayMoney) {
        this.hasPayMoney = hasPayMoney;
    }

    public Date getStayInTime() {
        return stayInTime;
    }

    public void setStayInTime(Date stayInTime) {
        this.stayInTime = stayInTime;
    }

    public Date getCheckOutTime() {
        return checkOutTime;
    }

    public void setCheckOutTime(Date checkOutTime) {
        this.checkOutTime = checkOutTime;
    }
}
