package bing.Pan.sso.mapper.mapperInterface;

import bing.Pan.sso.domain.entity.SsoSystemUser;
import bing.Pan.sso.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;


public interface SsoSystemUserMapper extends BaseMapper<SsoSystemUser,Object> {

    SsoSystemUser findUserByLoginName(@Param("loginName") String loginName);
}