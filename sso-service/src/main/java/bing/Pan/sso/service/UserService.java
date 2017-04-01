package bing.Pan.sso.service;

import bing.Pan.sso.domain.entity.SsoUser;
import bing.Pan.sso.mapper.mapperInterface.SsoUserMapper;
import bing.Pan.sso.service.config.dynamicDataSource.TargetDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @crea :Created by intelliJ IDEA 16.1.1 .
 * @auth :bing.Pan 15923508369@163.com .
 * @date :2017/1/17 18:52
 * @desc :
 */
@Service
@Transactional(readOnly = true)
public class UserService extends BaseService{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired private SsoUserMapper userMapper;

    @TargetDataSource("ds1")
    public SsoUser selectByPrimaryKey(){
        return userMapper.selectByPrimaryKey(1L);
    }




}
