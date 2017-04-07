package bing.Pan.sso.mapper;

import java.util.List;

/**
 * @crea :Created by intelliJ IDEA 16.1.1 .
 * @auth :bing.Pan  15923508369@163.com .
 * @date :2016/12/29 16:04
 * @desc :通用Mapper接口实现了基本的增删改查
 */

public interface BaseMapper<T,E> {

    int deleteByPrimaryKey(Long id);

    int insert(T record);

    int insertSelective(T record);

    T selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKey(T record);

    /**
     * 查询所有不带条件
     * @return
     */
    List<T> findList();

    /**
     * 根基条件查询List集合
     * @param custom 自定义类型
     * @return
     */
    List<T> findListByE(E custom);



    /**
     * 根基条件查询List集合
     * @param entity
     * @return
     */
    List<T> findListByT(T entity);





}
