package bing.Pan.sso.mapper.mapperInterface;

import bing.Pan.sso.domain.entity.SsoUser;
import bing.Pan.sso.mapper.BaseMapper;

public interface SsoUserMapper  extends BaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SsoUser record);

    int insertSelective(SsoUser record);

    SsoUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SsoUser record);

    int updateByPrimaryKey(SsoUser record);
}