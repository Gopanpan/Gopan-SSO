package bing.Pan.sso.service;


import bing.Pan.sso.common.utils.AESEncryptUtils;
import bing.Pan.sso.common.utils.Md5Utils;
import bing.Pan.sso.domain.entity.SysUser;
import bing.Pan.sso.service.config.properties.SsoSystemProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @crea :Created by intelliJ IDEA 16.1.1 .
 * @auth :bing.Pan 15923508369@163.com .
 * @date :2017/1/17 18:52
 * @desc :
 */

public class BaseService<T> {


    protected Logger logger =  LoggerFactory.getLogger(BaseService.class);

    @Autowired SsoSystemProperties ssoSystemProperties;

    //系统数据结构中通用可修改字段
    private static final String[] GENERAL_VALUE = new String[]{"password","createUser","createTime",
            "updateUser","updateTime","available"};




    /**
     * 验证是否是新增/修改 新增初始化默认字段，修改更新 更新时间，更新人
     * @param verifyEntity
     * @param sysUser
     * @return
     */
     T verifyEntity(T verifyEntity,SysUser sysUser) throws Exception {

        Class<?> aClass = verifyEntity.getClass();

        Field fieldId = aClass.getDeclaredField("id");
        fieldId.setAccessible(true);

        Object id = fieldId.get(verifyEntity);

        Field[] fields = verifyEntity.getClass().getDeclaredFields();
        if(StringUtils.isEmpty(id)){
            for(Field field:fields){
                field.setAccessible(true);
                String fieldName = field.getName();
                for (String general:GENERAL_VALUE) {
                    if(fieldName.equals(general)){
                        if(fieldName.equals(GENERAL_VALUE[0]))
                            field.set(verifyEntity,AESEncryptUtils.aesEncrypt(
                                    Md5Utils.md5(ssoSystemProperties.getUserDefaultPassword())));
                        if(fieldName.equals(GENERAL_VALUE[1]))
                            field.set(verifyEntity, sysUser.getId());
                        if(fieldName.equals(GENERAL_VALUE[2]))
                            field.set(verifyEntity, LocalDateTime.now());
                        if(fieldName.equals(GENERAL_VALUE[5]))
                            field.set(verifyEntity,true);
                    }
                }
            }

        }else{
            for(Field field:fields){
                field.setAccessible(true);
                String fieldName = field.getName();
                for (String general:GENERAL_VALUE) {
                    if(fieldName.equals(general)){
                        if(fieldName.equals(GENERAL_VALUE[3]))
                            field.set(verifyEntity,sysUser.getId());
                        if(fieldName.equals(GENERAL_VALUE[4]))
                            field.set(verifyEntity, LocalDateTime.now());

                    }
                }
            }
        }
        return verifyEntity;

    }
}
