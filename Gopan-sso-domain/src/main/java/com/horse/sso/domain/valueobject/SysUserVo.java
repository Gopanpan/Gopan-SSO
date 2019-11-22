package com.horse.sso.domain.valueobject;

import com.horse.sso.domain.entity.SysUser;

/**
 * @crea : Created by intelliJ IDEA 16.1.3
 * @auth : Gopan
 * @mail : 15923508369@163.com
 * @date : 2017/5/15 21:27
 * @desc :
 */
public class SysUserVo extends SysUser {


    private String createUserName;
    private String updateUserName;


    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public String getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }
}
