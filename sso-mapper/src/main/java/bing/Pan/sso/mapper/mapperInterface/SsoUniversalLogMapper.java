package bing.Pan.sso.mapper.mapperInterface;

import bing.Pan.sso.domain.entity.SsoUniversalLog;
import bing.Pan.sso.mapper.BaseMapper;

public interface SsoUniversalLogMapper  extends BaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SsoUniversalLog record);

    int insertSelective(SsoUniversalLog record);

    SsoUniversalLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SsoUniversalLog record);

    int updateByPrimaryKeyWithBLOBs(SsoUniversalLog record);

    int updateByPrimaryKey(SsoUniversalLog record);
}