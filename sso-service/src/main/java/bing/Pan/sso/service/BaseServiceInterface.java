package bing.Pan.sso.service;

import bing.Pan.sso.common.exception.ServiceException;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @crea : Created by intelliJ IDEA 16.1.3
 * @auth : bing.Pan
 * @mail : 15923508369@163.com
 * @date : 2017/5/29 17:42
 * @desc :
 */


@Component
public interface BaseServiceInterface<T,E,S> {

    int insertOrUpdate(T record,S currentLoginUser) throws Exception;

    int deleteById(Long id) throws ServiceException;


    Object findById(Long id) throws ServiceException;


    /**
     * 查询所有不带分页
     * @param e
     * @return
     * @throws ServiceException
     */
    List<T> findList(E e) throws ServiceException;

    /**
     * 根基条件查询分页List集合
     * @param customBo 自定义类型
     * @return
     */
    PageInfo findPageListByE(E customBo) throws ServiceException;



    /**
     * 根基条件查询分页List集合
     * @param entity
     * @return
     */
    PageInfo findPageListByT(T entity) throws ServiceException;



}
