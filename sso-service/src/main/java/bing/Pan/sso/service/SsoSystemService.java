package bing.Pan.sso.service;

import bing.Pan.sso.domain.bussinessobject.SsoSystemBo;
import bing.Pan.sso.domain.entity.SsoSystem;
import bing.Pan.sso.domain.entity.SysUser;
import bing.Pan.sso.domain.valueobject.SsoSystemVo;
import bing.Pan.sso.mapper.mapperInterface.SsoSystemMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @crea : Created by intelliJ IDEA 16.1.3
 * @auth : bing.Pan
 * @mail : 15923508369@163.com
 * @date : 2017/3/21 11:41
 * @desc :
 */

@Service
public class SsoSystemService extends BaseService implements BaseServiceInterface<SsoSystem,SsoSystemBo,SysUser>{


    @Autowired private SsoSystemMapper ssoSystemMapper;

    @Override
    @Transactional(readOnly = false,rollbackFor = Exception.class,propagation= Propagation.REQUIRED)
    public int insertOrUpdate(SsoSystem record, SysUser currentLoginUser) throws Exception {
        return 0;
    }

    @Override
    public int deleteById(Long id) throws Exception {
        return 0;
    }

    @Override
    public Object selectById(Long id) throws Exception {
        return null;
    }

    @Override
    public List<SsoSystem> findList() throws Exception {
        return null;
    }

    @Override
    public PageInfo findPageListByE(SsoSystemBo customBo) throws Exception {

        PageHelper.startPage(customBo.getPageIndex(),customBo.getPageSize());
        return new PageInfo<>(ssoSystemMapper.findListByE(customBo));
    }

    @Override
    public PageInfo findPageListByT(SsoSystem entity) throws Exception {
        return null;
    }
}
