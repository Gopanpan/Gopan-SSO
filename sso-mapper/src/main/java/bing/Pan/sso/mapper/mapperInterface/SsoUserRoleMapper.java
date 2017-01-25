package bing.Pan.sso.mapper.mapperInterface;

import bing.Pan.sso.domain.entity.SsoUserRole;
import bing.Pan.sso.mapper.BaseMapper;

public interface SsoUserRoleMapper  extends BaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SsoUserRole record);

    int insertSelective(SsoUserRole record);

    SsoUserRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SsoUserRole record);

    int updateByPrimaryKey(SsoUserRole record);
}