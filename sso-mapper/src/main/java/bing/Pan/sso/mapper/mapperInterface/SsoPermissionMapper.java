package bing.Pan.sso.mapper.mapperInterface;

import bing.Pan.sso.domain.entity.SsoPermission;
import bing.Pan.sso.mapper.BaseMapper;

public interface SsoPermissionMapper  extends BaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SsoPermission record);

    int insertSelective(SsoPermission record);

    SsoPermission selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SsoPermission record);

    int updateByPrimaryKey(SsoPermission record);
}