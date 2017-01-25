package bing.Pan.sso.mapper.mapperInterface;

import bing.Pan.sso.domain.entity.SsoUserSystem;
import bing.Pan.sso.mapper.BaseMapper;

public interface SsoUserSystemMapper  extends BaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SsoUserSystem record);

    int insertSelective(SsoUserSystem record);

    SsoUserSystem selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SsoUserSystem record);

    int updateByPrimaryKey(SsoUserSystem record);
}