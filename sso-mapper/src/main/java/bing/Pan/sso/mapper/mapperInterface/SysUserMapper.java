package bing.Pan.sso.mapper.mapperInterface;

import bing.Pan.sso.domain.entity.SysUser;
import bing.Pan.sso.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface SysUserMapper extends BaseMapper<SysUser,Object>{

    SysUser findUserByLoginName(@Param("loginName") String loginName);

}