package com.wt.overflow.datasource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;

import java.lang.reflect.Method;

/**
 * 暂时废弃
 */
@Order(1)
public class DynamicDataSourceAspect {
    /*
    //使用DateSource注解动作之后清除
    @After("@annotation(DateSource)")
    public void afterSwitchDS(JoinPoint point){
        System.out.println("清除当前数据源"+DataSourceContextHolder.getDB());
        DataSourceContextHolder.clearDB();
    }
    */
    //使用DateSource注解动态切换
    @Before("@annotation(DateSource)")
    public void beforeSwitchDS(JoinPoint point){
        //获得当前访问的class
        Class<?> className = point.getTarget().getClass();
        //获得访问的方法名
        String methodName = point.getSignature().getName();
        //得到方法的参数的类型
        Class[] argClass = ((MethodSignature)point.getSignature()).getParameterTypes();
        String dataSource = DataSourceContextHolder.DEFAULT_DS;
        try {
            // 得到访问的方法对象
            Method method = className.getMethod(methodName, argClass);
            // 判断是否存在@DS注解
            if (method.isAnnotationPresent(DateSource.class)) {
                DateSource annotation = method.getAnnotation(DateSource.class);
                // 取出注解中的数据源名
                dataSource = annotation.value();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 切换数据源
        DataSourceContextHolder.setDB(dataSource);
    }
}
