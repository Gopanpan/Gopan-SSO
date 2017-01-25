package bing.Pan.sso.mapper.mapperInterface;

import bing.Pan.sso.domain.entity.SsoUserOrg;
import bing.Pan.sso.mapper.BaseMapper;

public interface SsoUserOrgMapper extends BaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SsoUserOrg record);

    int insertSelective(SsoUserOrg record);

    SsoUserOrg selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SsoUserOrg record);

    int updateByPrimaryKey(SsoUserOrg record);
}