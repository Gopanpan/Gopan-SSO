package bing.Pan.sso.service;


import bing.Pan.sso.common.utils.AESEncryptUtils;
import bing.Pan.sso.common.utils.Md5Utils;
import bing.Pan.sso.service.config.properties.SsoSystemProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.Optional;

/**
 * @crea :Created by intelliJ IDEA 16.1.1 .
 * @auth :bing.Pan 15923508369@163.com .
 * @date :2017/1/17 18:52
 * @desc :
 */

public class BaseService<T> {


    public Logger logger =  LoggerFactory.getLogger(BaseService.class);

    @Autowired protected SsoSystemProperties ssoSystemProperties;



    //系统数据结构中通用字段
    private static final String[] GENERAL_VALUE = new String[]{"password","createTime","updateTime","available"};




    /**
     * 验证是否是新增/修改
     * @param verifyEntity
     * @return
     */
    protected T verifyEntity(T verifyEntity) throws Exception {

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
                            field.set(verifyEntity,AESEncryptUtils.aesEncrypt(Md5Utils.md5(ssoSystemProperties.getDefaultPassword())));
                        if(fieldName.equals(GENERAL_VALUE[1]) || fieldName.equals(GENERAL_VALUE[3]))
                            field.set(verifyEntity, new Date());
                    }
                }
            }

        }else{
            for(Field field:fields){
                field.setAccessible(true);
                String fieldName = field.getName();
                for (String general:GENERAL_VALUE) {
                    if(fieldName.equals(general)){
                        if(fieldName.equals(GENERAL_VALUE[2])){
                            field.set(verifyEntity,new Date());
                        }
                    }
                }
            }
        }
        return verifyEntity;

    }
}
