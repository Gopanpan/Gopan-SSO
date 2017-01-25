package bing.Pan.sso.mapper.mapperInterface;

import bing.Pan.sso.domain.entity.SsoOrganization;
import bing.Pan.sso.mapper.BaseMapper;

public interface SsoOrganizationMapper extends BaseMapper{
    int deleteByPrimaryKey(Long id);

    int insert(SsoOrganization record);

    int insertSelective(SsoOrganization record);

    SsoOrganization selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SsoOrganization record);

    int updateByPrimaryKey(SsoOrganization record);
}