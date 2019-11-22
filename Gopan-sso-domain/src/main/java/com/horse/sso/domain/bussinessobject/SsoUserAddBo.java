package com.horse.sso.domain.bussinessobject;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

/**
 * @crea : Created by intelliJ IDEA 16.1.3
 * @auth : Gopan
 * @mail : 15923508369@163.com
 * @date : 2017/5/14 22:43
 * @desc :
 */
public class SsoUserAddBo {

    private Long id;

    @NotBlank
    @Size(min=6,max=12,message="登录名长度应在6-12之间")
    private String loginName;

    @NotEmpty(message = "真实名称不能为空!")
    private String realName;

    @Size(max=11,min=11,message="长度只能为11位！")
    private String phone;

    @Min(value = 0,message = "性别枚举值(0女,1男,2未知)")
    @Max(value = 2,message = "性别枚举值(0女,1男,2未知)")
    private int sex;

    @Email(message = "电子邮件不正确")
    private String email;


    private String birthday;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}
