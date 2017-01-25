package bing.Pan.sso.service.config.dynamicDataSource;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @crea :Created by intelliJ IDEA 16.1.1 .
 * @auth :bing.Pan 15923508369@163.com .
 * @date :2017/1/17 10:01
 * @desc :指定数据源注解类
 */

@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TargetDataSource {
    String value();
}
