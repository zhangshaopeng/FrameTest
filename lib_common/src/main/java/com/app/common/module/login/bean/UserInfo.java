package com.app.common.module.login.bean;

import java.io.Serializable;

/**
 * <p>Description:
 * <p>Company:汇桔网
 * <p>Email:sundewu@wtoip.com
 * <p>@author:Created by Devin Sun on 2017/12/21.
 */
public class UserInfo implements Serializable {
    private String address;
    private String gender;
    private String nickName;
    private String recommend;
    private String avatar;
    private String token;
    private String phone;
    private String grade;
    private String loginName;
    private String registerStatus;
    private String id;
    private String memberId;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getRecommend() {
        return recommend;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getRegisterStatus() {
        return registerStatus;
    }

    public void setRegisterStatus(String registerStatus) {
        this.registerStatus = registerStatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "address='" + address + '\'' +
                ", gender='" + gender + '\'' +
                ", nickName='" + nickName + '\'' +
                ", recommend='" + recommend + '\'' +
                ", avatar='" + avatar + '\'' +
                ", token='" + token + '\'' +
                ", phone='" + phone + '\'' +
                ", grade='" + grade + '\'' +
                ", loginName='" + loginName + '\'' +
                ", registerStatus='" + registerStatus + '\'' +
                ", id='" + id + '\'' +
                ", memberId='" + memberId + '\'' +
                '}';
    }


}
