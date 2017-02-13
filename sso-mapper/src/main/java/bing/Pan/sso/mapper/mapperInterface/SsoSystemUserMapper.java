package bing.Pan.sso.mapper.mapperInterface;

import bing.Pan.sso.domain.entity.SsoSystemUser;
import bing.Pan.sso.domain.vObject.SystemUserVo;
import bing.Pan.sso.mapper.BaseMapper;

import java.util.List;

public interface SsoSystemUserMapper extends BaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SsoSystemUser record);

    int insertSelective(SsoSystemUser record);

    SsoSystemUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SsoSystemUser record);

    int updateByPrimaryKey(SsoSystemUser record);

    List systemUserList(SystemUserVo systemUserVo);
}