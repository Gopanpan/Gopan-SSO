package bing.Pan.sso.mapper.mapperInterface;

import bing.Pan.sso.domain.entity.SsoSystemUser;

public interface SsoSystemUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SsoSystemUser record);

    int insertSelective(SsoSystemUser record);

    SsoSystemUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SsoSystemUser record);

    int updateByPrimaryKey(SsoSystemUser record);
}