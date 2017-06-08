package bing.Pan.sso.domain.bussinessobject;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

/**
 * @crea : Created by intelliJ IDEA 16.1.3
 * @auth : bing.Pan
 * @mail : 15923508369@163.com
 * @date : 2017/5/14 22:43
 * @desc :
 */
public class SsoSystemAddBo {

    private Long id;

    @NotBlank
    @Size(min=3,max=12,message="系统名称应在3-12之间")
    private String name;

    @NotEmpty(message = "系统编码不能为空!")
    private String systemCode;

    @NotEmpty(message = "系统状态不能为空")
    private Boolean available;


    private String detailExplain;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public String getDetailExplain() {
        return detailExplain;
    }

    public void setDetailExplain(String detailExplain) {
        this.detailExplain = detailExplain;
    }
}
