package bing.Pan.sso.domain.valueobject;

import bing.Pan.sso.domain.entity.SsoSystem;

/**
 * @crea : Created by intelliJ IDEA 16.1.3
 * @auth : bing.Pan
 * @mail : 15923508369@163.com
 * @date : 2017/5/29 17:12
 * @desc :
 */
public class SsoSystemVo extends SsoSystem {

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
