package com.horse.sso.service.config;

import com.horse.sso.domain.entity.SysJob;
import com.horse.sso.mapper.mapperInterface.SysJobMapper;
import com.horse.sso.service.ScheduleService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;


/**
 * @crea : Created by intelliJ IDEA 16.1.3
 * @auth : Gopan
 * @mail : 15923508369@163.com
 * @date : 2017/6/27 17:27
 * @desc :
 */
@Component
public class InitJobs {
    private Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());


    @Autowired private ScheduleService scheduleService;
    @Autowired private SysJobMapper sysJobMapper;

    @PostConstruct
    private void init() {
        logger.info("初始化系统自动任务");
        List<SysJob> jobList = sysJobMapper.findList();
        jobList.forEach(sysJob -> {
            try {
                scheduleService.addJob(sysJob);
            } catch (Exception e) {
                logger.info("初始化系统自动任务时异常,堆栈信息：{}",e);
            }
        });

    }
}
