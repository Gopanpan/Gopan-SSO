package bing.Pan.sso.service.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @crea : Created by intelliJ IDEA 16.1.3
 * @auth : bing.Pan
 * @mail : 15923508369@163.com
 * @date : 2017/5/14 21:49
 * @desc : 系统配置文件
 */

@ConfigurationProperties(locations = "classpath:system.properties", prefix = "sso.user")
@Component
public class SsoSystemProperties {

    private String defaultPassword;


    public String getDefaultPassword() {
        return defaultPassword;
    }

    public void setDefaultPassword(String defaultPassword) {
        this.defaultPassword = defaultPassword;
    }
}

