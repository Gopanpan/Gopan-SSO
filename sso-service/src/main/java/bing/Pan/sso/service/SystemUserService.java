package bing.Pan.sso.service;

import bing.Pan.sso.common.enums.ResponseCode;
import bing.Pan.sso.common.exception.ServiceException;
import bing.Pan.sso.common.utils.EncryptUtils;
import bing.Pan.sso.common.utils.Md5Utils;
import bing.Pan.sso.domain.bussinessobject.SystemUserBo;
import bing.Pan.sso.domain.entity.SysUser;
import bing.Pan.sso.domain.valueobject.SysUserVo;
import bing.Pan.sso.mapper.mapperInterface.SysUserMapper;
import bing.Pan.sso.service.config.properties.SsoSystemProperties;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.Date;


/**
 * @crea : Created by intelliJ IDEA 16.1.3
 * @auth : bing.Pan
 * @mail : 15923508369@163.com
 * @date : 2017/2/6 20:27
 * @desc :
 */

@Service
public class SystemUserService extends BaseService {

    @Autowired private SsoSystemProperties ssoSystemProperties;
    @Autowired private SysUserMapper sysUserMapper;


    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Object systemUserList(SystemUserBo systemUserBo) {
        PageHelper.startPage(systemUserBo.getPageIndex(), systemUserBo.getPageSize());
        return new PageInfo<>(sysUserMapper.findListByE(systemUserBo));
    }

    @Transactional(readOnly = true)
    public Object getSystemUserById(Long id) throws ServiceException {
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(id);
        SysUser createUser = sysUserMapper.selectByPrimaryKey(sysUser.getCreateUser());
        SysUser updateUser = sysUserMapper.selectByPrimaryKey(sysUser.getUpdateUser());
        SysUserVo sysUserVo = new SysUserVo();

        BeanUtils.copyProperties(sysUser,sysUserVo);
        if(!ObjectUtils.isEmpty(createUser))
            sysUserVo.setCreateUserName(createUser.getLoginName());
        if(!ObjectUtils.isEmpty(updateUser))
            sysUserVo.setUpdateUserName(updateUser.getLoginName());

        return sysUserVo;
    }
    @Transactional(readOnly = true)
    public SysUser findByLoginName(String loginName, String password) throws Exception {
        SysUser sysUser = sysUserMapper.findUserByLoginName(loginName);
        if(ObjectUtils.isEmpty(sysUser))
            throw new ServiceException(ResponseCode.LOGIN_USER_MISS);
        if(!sysUser.getAvailable()){
            throw new ServiceException(ResponseCode.LOGIN_USER_NOT_AVAILABLE);
        }
        if(!EncryptUtils.aesEncrypt(password).equals(sysUser.getPassword()))
            throw new ServiceException(ResponseCode.LOGIN_PASSWORD_ERR);

        return sysUser;

    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void addAUpdateSysUser(SysUser sysUser) throws Exception {

        if(StringUtils.isEmpty(sysUser.getId())){
            if(StringUtils.isEmpty(sysUser.getPassword()))
                sysUser.setPassword(EncryptUtils.aesEncrypt(Md5Utils.md5(ssoSystemProperties.getDefaultPassword())));

            sysUser.setCreateTime(new Date());
            sysUser.setUpdateTime(new Date());
            sysUser.setAvailable(true);
            sysUserMapper.insert(sysUser);
        }else
            sysUser.setUpdateTime(new Date());
            sysUserMapper.updateByPrimaryKeySelective(sysUser);
    }

    @Transactional(readOnly = true)
    public SysUser findByLoginName(String loginName) {
        return sysUserMapper.findUserByLoginName(loginName);
    }
}
