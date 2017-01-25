package bing.Pan.sso.mapper.mapperInterface;

import bing.Pan.sso.domain.entity.SsoRoleRes;
import bing.Pan.sso.mapper.BaseMapper;

public interface SsoRoleResMapper extends BaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SsoRoleRes record);

    int insertSelective(SsoRoleRes record);

    SsoRoleRes selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SsoRoleRes record);

    int updateByPrimaryKey(SsoRoleRes record);
}