package bing.Pan.sso.service;

import bing.Pan.sso.domain.vObject.BaseVo;
import bing.Pan.sso.mapper.mapperInterface.SsoSystemMapper;
import bing.Pan.sso.service.config.dynamicDataSource.TargetDataSource;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @crea : Created by intelliJ IDEA 16.1.3
 * @auth : bing.Pan
 * @mail : 15923508369@163.com
 * @date : 2017/3/21 11:41
 * @desc :
 */

@Service
public class SystemInfoService {

    @Autowired SsoSystemMapper systemMapper;

    @TargetDataSource("ds3")
    public Object getSysInfoPageList(BaseVo baseVo) {
        PageHelper.startPage(baseVo.getPageIndex(),baseVo.getPageSize());
        return  new PageInfo(systemMapper.systemUserList());
    }
}
