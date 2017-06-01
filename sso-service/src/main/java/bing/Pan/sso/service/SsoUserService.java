package bing.Pan.sso.service;

import bing.Pan.sso.domain.bussinessobject.SsoUserBo;
import bing.Pan.sso.domain.entity.SsoUser;
import bing.Pan.sso.domain.entity.SysUser;
import bing.Pan.sso.domain.valueobject.SsoUserVo;
import bing.Pan.sso.mapper.mapperInterface.SsoUserMapper;
import bing.Pan.sso.mapper.mapperInterface.SysUserMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @crea :Created by intelliJ IDEA 16.1.1 .
 * @auth :bing.Pan 15923508369@163.com .
 * @date :2017/1/17 18:52
 * @desc :
 */
@Service
public class SsoUserService extends BaseService  implements BaseServiceInterface<SsoUser,SsoUserBo,SysUser>{
    
    @Autowired private SsoUserMapper ssoUserMapper;
    @Autowired private SysUserMapper sysUserMapper;


    @Transactional(readOnly = true)
    public SysUser findByLoginName(String loginName) throws Exception {
        return ssoUserMapper.findUserByLoginName(loginName);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public int insertOrUpdate(SsoUser record, SysUser currentLoginUser) throws Exception {

        SsoUser ssoUserTemp = (SsoUser)verifyEntity(record,currentLoginUser);
        if(StringUtils.isEmpty(ssoUserTemp.getId()))
           return ssoUserMapper.insert(ssoUserTemp);
        else
           return ssoUserMapper.updateByPrimaryKeySelective(ssoUserTemp);

    }

    @Override
    @Transactional(readOnly = false,propagation = Propagation.REQUIRED)
    public int deleteById(Long id) throws Exception {
        return ssoUserMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Object selectById(Long id) throws Exception {
        SsoUser ssoUser = ssoUserMapper.selectByPrimaryKey(id);
        SysUser createUser = sysUserMapper.selectByPrimaryKey(ssoUser.getCreateUser());
        SysUser updateUser = sysUserMapper.selectByPrimaryKey(ssoUser.getUpdateUser());
        SsoUserVo ssoUserVo = new SsoUserVo();

        BeanUtils.copyProperties(ssoUser, ssoUserVo);
        if(!ObjectUtils.isEmpty(createUser))
            ssoUserVo.setCreateUserName(createUser.getLoginName());
        if(!ObjectUtils.isEmpty(updateUser))
            ssoUserVo.setUpdateUserName(updateUser.getLoginName());

        return ssoUserVo;
    }

    @Override
    public List<SsoUser> findList(SsoUserBo ssoUserBo) throws Exception {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public PageInfo findPageListByE(SsoUserBo customBo) throws Exception  {
        PageHelper.startPage(customBo.getPageIndex(), customBo.getPageSize());
        return new PageInfo<>(ssoUserMapper.findListByE(customBo));
    }

    @Override
    public PageInfo findPageListByT(SsoUser entity) throws Exception  {
        return null;
    }
}
