package bing.Pan.sso.service.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @crea : Created by intelliJ IDEA 16.1.3
 * @auth : bing.Pan
 * @mail : 15923508369@163.com
 * @date : 2017/5/14 21:49
 * @desc : 系统配置文件
 */

@Component
@ConfigurationProperties(prefix = "sso.system")
@PropertySource("classpath:system.properties")
public class SsoSystemProperties {

    private String     userDefaultPassword;                  //系统初始用户密码



    public String getUserDefaultPassword() {
        return userDefaultPassword;
    }

    public void setUserDefaultPassword(String userDefaultPassword) {
        this.userDefaultPassword = userDefaultPassword;
    }
}

