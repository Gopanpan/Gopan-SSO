package bing.Pan.sso.service;

import bing.Pan.sso.common.exception.ServiceException;
import bing.Pan.sso.domain.bussinessobject.SsoSystemBo;
import bing.Pan.sso.domain.entity.SsoSystem;
import bing.Pan.sso.domain.entity.SysUser;
import bing.Pan.sso.mapper.mapperInterface.SsoSystemMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @crea : Created by intelliJ IDEA 16.1.3
 * @auth : bing.Pan
 * @mail : 15923508369@163.com
 * @date : 2017/3/21 11:41
 * @desc :
 */

@Service
public class SsoSystemService extends BaseService<SsoSystem> implements BaseServiceInterface<SsoSystem,SsoSystemBo,SysUser>{


    @Autowired private SsoSystemMapper ssoSystemMapper;

    @Override
    @Transactional(rollbackFor = Exception.class,propagation= Propagation.REQUIRED)
    public int insertOrUpdate(SsoSystem record, SysUser currentLoginUser) throws Exception {
        record = verifyEntity(record, currentLoginUser);
        if(StringUtils.isEmpty(record.getId()))
            return ssoSystemMapper.insert(record);
        else
            return ssoSystemMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int deleteById(Long id) throws ServiceException {
        return 0;
    }



    @Override
    public Object findById(Long id) throws ServiceException {
        return ssoSystemMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SsoSystem> findListByE(SsoSystemBo customBo) throws ServiceException {
        return null;
    }


    @Override
    public PageInfo findPageListByE(SsoSystemBo customBo) throws ServiceException {

        PageHelper.startPage(customBo.getPageIndex(),customBo.getPageSize());
        return new PageInfo<>(ssoSystemMapper.findListByE(customBo));
    }

    @Override
    public PageInfo findListByT(SsoSystem entity) throws ServiceException {
        return null;
    }

    @Override
    public PageInfo findPageListByT(SsoSystem entity) throws ServiceException {
        return null;
    }
}
