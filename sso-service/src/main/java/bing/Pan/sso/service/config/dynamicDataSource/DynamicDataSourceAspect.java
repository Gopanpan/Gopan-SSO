package bing.Pan.sso.service.config.dynamicDataSource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @crea :Created by intelliJ IDEA 16.1.1 .
 * @auth :bing.Pan 15923508369@163.com .
 * @date :2017/1/17 10:03
 * @desc :动态切换数据源
 */

@Aspect
@Order(-10)//保证该AOP在@Transactional之前执行
@Component
public class DynamicDataSourceAspect {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    /*
     * @Before("@annotation(ds)")
     * 的意思是：
     * @Before：在方法执行之前进行执行：
     * @annotation(targetDataSource)：
     * 会拦截注解targetDataSource的方法，否则不拦截;
     */
    @Before("@annotation(targetDataSource)")
    public void changeDataSource(JoinPoint point, TargetDataSource targetDataSource) throws Throwable {
        //获取当前的指定的数据源;
        String dsId = targetDataSource.value();
        //如果不在我们注入的所有的数据源范围之内，那么输出警告信息，系统自动使用默认的数据源。
        if (!DynamicDataSourceContextHolder.containsDataSource(dsId)) {
            log.error(String.format("数据源[{%s}]不存在，使用默认数据源,执行方法为：[{%s}]",dsId,point.getSignature()));
        } else {
            System.out.println("UseDataSource : {} > {}"+targetDataSource.value()+point.getSignature());
            DynamicDataSourceContextHolder.setDataSourceType(targetDataSource.value());
        }
    }

    @After("@annotation(targetDataSource)")
    public void restoreDataSource(JoinPoint point, TargetDataSource targetDataSource) {
        //方法执行完毕之后，销毁当前数据源信息，进行垃圾回收。
        DynamicDataSourceContextHolder.clearDataSourceType();
    }

}
