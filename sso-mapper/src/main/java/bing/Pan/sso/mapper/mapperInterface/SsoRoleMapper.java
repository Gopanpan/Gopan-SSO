package bing.Pan.sso.mapper.mapperInterface;

import bing.Pan.sso.domain.entity.SsoRole;
import bing.Pan.sso.mapper.BaseMapper;

public interface SsoRoleMapper extends BaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SsoRole record);

    int insertSelective(SsoRole record);

    SsoRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SsoRole record);

    int updateByPrimaryKey(SsoRole record);
}