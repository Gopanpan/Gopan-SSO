package com.horse.sso.domain.valueobject;

import com.horse.sso.domain.entity.SsoUser;

public class SsoUserVo extends SsoUser{


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