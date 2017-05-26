package bing.Pan.sso.domain.valueobject;

import bing.Pan.sso.domain.entity.SsoUser;

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