package bing.Pan.sso.service;

import bing.Pan.sso.common.enums.ResponseCode;
import bing.Pan.sso.common.exception.ServiceException;
import bing.Pan.sso.domain.vObject.SystemUserVo;
import bing.Pan.sso.mapper.mapperInterface.SsoSystemUserMapper;
import bing.Pan.sso.service.config.dynamicDataSource.TargetDataSource;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @crea : Created by intelliJ IDEA 16.1.3
 * @auth : bing.Pan
 * @mail : 15923508369@163.com
 * @date : 2017/2/6 20:27
 * @desc :
 */

@Service
public class SystemUserService {

    @Autowired private SsoSystemUserMapper systemUserMapper;

    @TargetDataSource("ds1")
    public Object systemUserList(SystemUserVo systemUserVo) {
        PageHelper.startPage(systemUserVo.getPageIndex(),systemUserVo.getPageSize());
        return new PageInfo(systemUserMapper.systemUserList(systemUserVo));
    }

    @TargetDataSource("ds1")
    public Object getSystemUserById(Long sysUserId) throws ServiceException {
        if(null == sysUserId) throw new ServiceException(ResponseCode.CLIENT_PARAM_ERR);
        return systemUserMapper.selectByPrimaryKey(sysUserId);
    }
}
