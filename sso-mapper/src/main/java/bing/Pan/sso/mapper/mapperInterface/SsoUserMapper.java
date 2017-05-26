package bing.Pan.sso.mapper.mapperInterface;

import bing.Pan.sso.domain.entity.SsoUser;
import bing.Pan.sso.domain.entity.SysUser;
import bing.Pan.sso.mapper.BaseMapper;

public interface SsoUserMapper extends BaseMapper<SsoUser,Object> {
    SysUser findUserByLoginName(String loginName);
}