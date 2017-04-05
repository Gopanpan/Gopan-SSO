package bing.Pan.sso.mapper.mapperInterface;

import bing.Pan.sso.domain.entity.SsoSystemUser;
import bing.Pan.sso.domain.entity.SsoUser;
import bing.Pan.sso.mapper.BaseMapper;

public interface SsoUserMapper   extends BaseMapper<SsoSystemUser,Object>  {

    SsoSystemUser findUserByLoginName(String userName);
}