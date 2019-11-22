package com.horse.sso.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @crea : Created by intelliJ IDEA 16.1.3
 * @auth : Gopan
 * @mail : 15923508369@163.com
 * @date : 2017/6/3 14:04
 * @desc :
 */
public class BeanMapUtil {
    private static Logger logger =  LoggerFactory.getLogger(BeanMapUtil.class);

    /**
     * 对象转map
     * @param obj
     * @return
     */
    public static Map<String, Object> beanTrans2Map(Object obj) {

        if (StringUtils.isEmpty(obj))
            return null;


        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            Map<String, Object> map = new HashMap<>();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                if (!key.equals("class")) {
                    Method getter = property.getReadMethod();
                    Object value = getter.invoke(obj);

                    map.put(key, value);
                }
            }
            return map;
        } catch (Exception e) {
            logger.info("对象转map发生异常");
            logger.info("堆栈信息：", e);
        }
        return null;
    }



    /**
     * Map 对象转化为一个 JavaBean
     * @param type 要转化的类型
     * @param map 包含属性值的 map
     * @return 转化出来的 JavaBean 对象
     */
    public static Object convertMap(Class type, Map map){
        try{
            BeanInfo beanInfo = Introspector.getBeanInfo(type); // 获取类属性
            Object obj = type.newInstance(); // 创建 JavaBean 对象

            // 给 JavaBean 对象的属性赋值
            PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor descriptor: propertyDescriptors) {
                String propertyName = descriptor.getName();
                if (map.containsKey(propertyName)) {
                    Object value = map.get(propertyName);
                    Object[] args = new Object[1];
                    args[0] = value;

                    descriptor.getWriteMethod().invoke(obj, args);
                }
            }
            return obj;
        }catch (Exception e){
            logger.info("map实例化对象发生异常");
            logger.info("堆栈信息：", e);

        }
        return null;

    }
}
