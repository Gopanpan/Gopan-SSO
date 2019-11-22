package com.horse.sso.manage;


import com.horse.sso.service.config.dynamicDataSource.DynamicDataSourceRegister;
import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import java.util.concurrent.TimeUnit;

/**
 * @crea :Created by intelliJ IDEA 16.1.1 .
 * @auth :Gopan 15923508369@163.com .
 * @date :2017/1/16 20:02
 * @desc :
 */
@SpringBootApplication
@ComponentScan(value = "com.horse")
@ServletComponentScan
@Import({DynamicDataSourceRegister.class})
public class SsoManagerApplication {

    @Value("${server.session.timeout}")
    private int serverSessionTimeout;



    public static void main(String[] args) {

        SpringApplication.run(SsoManagerApplication.class, args);

    }



    /**
     * 配置 Spring boot tomcat 相关
     * @return
     */
    @Bean
    public EmbeddedServletContainerCustomizer embeddedServletContainerCustomizer() {

        return (ConfigurableEmbeddedServletContainer container) ->
            container.setSessionTimeout(serverSessionTimeout, TimeUnit.MINUTES);


    }


    /**
     * http --> https
     * @return
     */
    @Bean
    public EmbeddedServletContainerFactory servletContainer() {
        TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory() {
            @Override
            protected void postProcessContext(Context context) {
                SecurityConstraint constraint = new SecurityConstraint();
                constraint.setUserConstraint("CONFIDENTIAL");
                SecurityCollection collection = new SecurityCollection();
                collection.addPattern("/*");
                constraint.addCollection(collection);
                context.addConstraint(constraint);
            }
        };
        tomcat.addAdditionalTomcatConnectors(httpConnector());
        return tomcat;
    }

    @Bean
    public Connector httpConnector() {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setScheme("http");
        connector.setPort(2323);
        connector.setSecure(false);
        connector.setRedirectPort(3333);
        return connector;
    }


}
