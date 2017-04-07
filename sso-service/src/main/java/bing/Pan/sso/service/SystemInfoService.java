package bing.Pan.sso.service;

import bing.Pan.sso.common.enums.ResponseCode;
import bing.Pan.sso.common.response.Response;
import bing.Pan.sso.domain.vObject.BaseVo;
import bing.Pan.sso.mapper.mapperInterface.SsoSystemMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @crea : Created by intelliJ IDEA 16.1.3
 * @auth : bing.Pan
 * @mail : 15923508369@163.com
 * @date : 2017/3/21 11:41
 * @desc :
 */

@Service
@Transactional(readOnly = true)
public class SystemInfoService extends BaseService{
    @Autowired private SsoSystemMapper ssoSystemMapper;

    public Object sysInfoPageList(BaseVo baseVo) {
        PageHelper.startPage(baseVo.getPageIndex(),baseVo.getPageSize());
        return  new PageInfo(ssoSystemMapper.findListByE(baseVo));
    }
}
