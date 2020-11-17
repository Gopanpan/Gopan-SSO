package com.horse.sso.mapper.mapperInterface;

import com.horse.sso.domain.entity.SysUser;
import com.horse.sso.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface SysUserMapper extends BaseMapper<SysUser,Object>{

    SysUser findUserByLoginName(@Param("loginName") String loginName);

}