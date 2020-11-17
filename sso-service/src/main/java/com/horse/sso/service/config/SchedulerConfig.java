package com.horse.sso.service.config;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.AdaptableJobFactory;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * @crea : Created by intelliJ IDEA 16.1.3
 * @auth : Gopan
 * @mail : 15923508369@163.com
 * @date : 2017/6/27 16:29
 * @desc :
 */
@Configuration
public class SchedulerConfig {

    @Bean
    public SchedulerFactoryBean getSchedulerFactoryBean() {
        SchedulerFactoryBean bean = new SchedulerFactoryBean();
        bean.setJobFactory(getJobFactory());
        return bean;
    }

    @Bean
    public SysJobFactory getJobFactory() {
        return new SysJobFactory();
    }



    public class SysJobFactory extends AdaptableJobFactory {

        @Autowired
        private AutowireCapableBeanFactory capableBeanFactory;

        protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
            Object jobInstance = super.createJobInstance(bundle);
            capableBeanFactory.autowireBean(jobInstance);
            return jobInstance;
        }

    }
}
