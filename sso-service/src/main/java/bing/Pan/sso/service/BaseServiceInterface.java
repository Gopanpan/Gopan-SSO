package bing.Pan.sso.service;

import bing.Pan.sso.common.exception.ServiceException;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @crea : Created by intelliJ IDEA 16.1.3
 * @auth : bing.Pan
 * @mail : 15923508369@163.com
 * @date : 2017/6/16 17:42
 * @desc : 通用service接口,接口说明
 *              T 当前接口所对应的基础实体
 *              E 当前接口所对应的自定义实体
 *              S 当前系统登录用户实体
 *
 *              如果E在具体实现类中没有定义可以使用Object类代替
 */


@Component
public interface BaseServiceInterface<T,E,S> {

    int insertOrUpdate(T record, S currentLoginUser) throws Exception;

    int deleteById(Long id) throws ServiceException;


    Object findById(Long id) throws ServiceException;


    /**
     * 查询所有不带分页
     * @param customBo     自定义查询对象
     * @return
     * @throws ServiceException
     */
    List<T> findListByE(E customBo) throws ServiceException;


    /**
     * 根据条件查询分页List集合
     * @param customBo     自定义查询对象
     * @return
     */
    PageInfo findPageListByE(E customBo) throws ServiceException;





    /**
     * 查询所有不带分页
     * @param entity      实体基类
     * @return
     */
    PageInfo findListByT(T entity) throws ServiceException;

    /**
     * 根据条件查询分页List集合
     * @param entity      实体基类
     * @return
     */
    PageInfo findPageListByT(T entity) throws ServiceException;



}
