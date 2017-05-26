package bing.Pan.sso.service;

import bing.Pan.sso.common.exception.ServiceException;
import bing.Pan.sso.common.utils.AESEncryptUtils;
import bing.Pan.sso.common.utils.Md5Utils;
import bing.Pan.sso.domain.bussinessobject.SsoUserBo;
import bing.Pan.sso.domain.entity.SsoUser;
import bing.Pan.sso.domain.entity.SysUser;
import bing.Pan.sso.domain.valueobject.SsoUserVo;
import bing.Pan.sso.mapper.mapperInterface.SsoUserMapper;
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
 * @crea :Created by intelliJ IDEA 16.1.1 .
 * @auth :bing.Pan 15923508369@163.com .
 * @date :2017/1/17 18:52
 * @desc :
 */
@Service

public class SsoUserService extends BaseService{
    
    @Autowired private SsoUserMapper ssoUserMapper;


    @Transactional(readOnly = true)
    public Object ssoUserList(SsoUserBo ssoUserBo) {
        PageHelper.startPage(ssoUserBo.getPageIndex(), ssoUserBo.getPageSize());
        return new PageInfo<>(ssoUserMapper.findListByE(ssoUserBo));
    }

    @Transactional(readOnly = true)
    public Object getSsoUserById(Long id) throws ServiceException {
        SsoUser ssoUser = ssoUserMapper.selectByPrimaryKey(id);
        SsoUser createUser = ssoUserMapper.selectByPrimaryKey(ssoUser.getCreateUser());
        SsoUser updateUser = ssoUserMapper.selectByPrimaryKey(ssoUser.getUpdateUser());
        SsoUserVo ssoUserVo = new SsoUserVo();

        BeanUtils.copyProperties(ssoUser, ssoUserVo);
        if(!ObjectUtils.isEmpty(createUser))
            ssoUserVo.setCreateUserName(createUser.getLoginName());
        if(!ObjectUtils.isEmpty(updateUser))
            ssoUserVo.setUpdateUserName(updateUser.getLoginName());

        return ssoUserVo;
    }


    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void addAUpdateSsoUser(SsoUser ssoUser, SysUser currentUser) throws Exception {


        SsoUser ssoUserTemp = (SsoUser)verifyEntity(ssoUser,currentUser);
        if(StringUtils.isEmpty(ssoUserTemp.getId()))
            ssoUserMapper.insert(ssoUserTemp);
        else
            ssoUserMapper.updateByPrimaryKeySelective(ssoUserTemp);

    }

    @Transactional(readOnly = true)
    public SysUser findByLoginName(String loginName) {
        return ssoUserMapper.findUserByLoginName(loginName);
    }

    @Transactional(readOnly = false,propagation = Propagation.REQUIRED)
    public void deleteSsoUserById(Long sysUserId) {
        ssoUserMapper.deleteByPrimaryKey(sysUserId);
    }


}
