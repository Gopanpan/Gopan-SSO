package com.horse.sso.mapper.mapperInterface;

import com.horse.sso.domain.entity.SsoUser;
import com.horse.sso.domain.entity.SysUser;
import com.horse.sso.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface SsoUserMapper extends BaseMapper<SsoUser,Object> {

    SysUser findUserByLoginName(@Param("loginName") String loginName);
}