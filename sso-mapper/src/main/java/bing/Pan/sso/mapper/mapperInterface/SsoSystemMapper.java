package bing.Pan.sso.mapper.mapperInterface;

import bing.Pan.sso.domain.entity.SsoSystem;
import bing.Pan.sso.mapper.BaseMapper;

import java.util.List;

public interface SsoSystemMapper  extends BaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SsoSystem record);

    int insertSelective(SsoSystem record);

    SsoSystem selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SsoSystem record);

    int updateByPrimaryKey(SsoSystem record);

    List systemUserList();

}