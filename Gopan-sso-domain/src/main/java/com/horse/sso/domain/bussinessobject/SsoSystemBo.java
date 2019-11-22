package com.horse.sso.domain.bussinessobject;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @crea : Created by intelliJ IDEA 16.1.3
 * @auth : Gopan
 * @mail : 15923508369@163.com
 * @date : 2017/5/29 17:14
 * @desc :
 */
@ApiModel
public class SsoSystemBo extends PageBo{

    @ApiModelProperty("系统名称")
    private String name;

    @ApiModelProperty("系统编码")
    private String systemCode;

    @ApiModelProperty("是否启用")
    private boolean available;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSystemCode() {
        return systemCode;
    }

    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
