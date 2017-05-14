package bing.Pan.sso.service;

import bing.Pan.sso.common.enums.ResponseCode;
import bing.Pan.sso.common.exception.ServiceException;
import bing.Pan.sso.common.utils.EncryptUtils;
import bing.Pan.sso.domain.bussinessobject.SystemUserBo;
import bing.Pan.sso.domain.entity.SysUser;
import bing.Pan.sso.mapper.mapperInterface.SysUserMapper;
import bing.Pan.sso.service.config.properties.SsoSystemProperties;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;


/**
 * @crea : Created by intelliJ IDEA 16.1.3
 * @auth : bing.Pan
 * @mail : 15923508369@163.com
 * @date : 2017/2/6 20:27
 * @desc :
 */

@Service
@Transactional(readOnly = true)
public class SystemUserService extends BaseService {

    @Autowired private SsoSystemProperties ssoSystemProperties;
    @Autowired private SysUserMapper sysUserMapper;


    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Object systemUserList(SystemUserBo systemUserBo) {
        PageHelper.startPage(systemUserBo.getPageIndex(), systemUserBo.getPageSize());
        return new PageInfo<>(sysUserMapper.findListByE(systemUserBo));
    }


    public Object getSystemUserById(Long sysUserId) throws ServiceException {
        if(null == sysUserId) throw new ServiceException(ResponseCode.CLIENT_PARAM_ERR);
        return sysUserMapper.selectByPrimaryKey(sysUserId);
    }

    public SysUser findUserByLoginName(String loginName, String password) throws ServiceException {
        SysUser systemUser = sysUserMapper.findUserByLoginName(loginName);
        if(ObjectUtils.isEmpty(systemUser))
            throw new ServiceException(ResponseCode.LOGIN_USER_MISS);
        if(!systemUser.getPassword().equals(password))
            throw new ServiceException(ResponseCode.LOGIN_PASSWORD_ERR);

        return systemUser;

    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void addAUpdateSysUser(SysUser sysUser) throws Exception {
        if(StringUtils.isEmpty(sysUser.getId())){
            if(StringUtils.isEmpty(sysUser.getPassword()))
                sysUser.setPassword(EncryptUtils.aesEncrypt(ssoSystemProperties.getDefaultPassword()));
            sysUserMapper.insert(sysUser);
        }else
            sysUserMapper.updateByPrimaryKeySelective(sysUser);
    }
}
