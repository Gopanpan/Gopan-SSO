package com.horse.sso.service;

import com.horse.sso.common.exception.ServiceException;
import com.horse.sso.domain.entity.SysJob;
import com.horse.sso.domain.entity.SysUser;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @crea : Created by intelliJ IDEA 16.1.3
 * @auth : Gopan
 * @mail : 15923508369@163.com
 * @date : 2017/6/27 17:37
 * @desc :
 */
@Service
public class SysJobService extends BaseService<SysJob>  implements BaseServiceInterface<SysJob,Object,SysUser> {
    @Override
    public int insertOrUpdate(SysJob record, SysUser currentLoginUser) throws Exception {
        return 0;
    }

    @Override
    public int deleteById(Long id) throws ServiceException {
        return 0;
    }

    @Override
    public Object findById(Long id) throws ServiceException {
        return null;
    }

    @Override
    public List<SysJob> findListByE(Object customBo) throws ServiceException {
        return null;
    }

    @Override
    public PageInfo findPageListByE(Object customBo) throws ServiceException {
        return null;
    }

    @Override
    public PageInfo findListByT(SysJob entity) throws ServiceException {
        return null;
    }

    @Override
    public PageInfo findPageListByT(SysJob entity) throws ServiceException {
        return null;
    }
}
