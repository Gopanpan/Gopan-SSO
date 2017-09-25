package bing.Pan.sso;

import bing.Pan.sso.common.utils.BeanMapUtil;
import bing.Pan.sso.domain.entity.SysUser;
import com.google.common.collect.Maps;
import org.apache.commons.beanutils.BeanUtils;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;

/**
 * @crea : Created by intelliJ IDEA 16.1.3
 * @auth : bing.Pan
 * @mail : 15923508369@163.com
 * @date : 2017/6/3 14:11
 * @desc :
 */
public class BeanMapMutualConversionTest {

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, IntrospectionException, InstantiationException {
        SysUser sysUser = new SysUser();
        sysUser.setId(1L);
        sysUser.setEmail("15923508369@163.com");
        sysUser.setCreateTime(LocalDateTime.now());

        Map<String, Object> stringObjectMap = BeanMapUtil.beanTrans2Map(sysUser);


        System.out.println(stringObjectMap.toString());


        SysUser user = new SysUser();
        Object o = BeanMapUtil.convertMap(user.getClass(), stringObjectMap);
        System.out.println(o.toString());

    }
}
