package com.wt.overflow.datasource;

/**
 * 暂时废弃
 */
public class DataSourceContextHolder {

    /**
     * 默认数据源
     */
    public static final String DEFAULT_DS = "datasource1";

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    // 设置数据源名
    public static void setDB(String dbType) {
        System.out.println("切换到{"+dbType+"}数据源");
        contextHolder.set(dbType);
    }

    // 获取数据源名
    public static String getDB() {
        //return (contextHolder.get());
        if(contextHolder.get()==null){
            return DEFAULT_DS;
        }else{
            return (contextHolder.get());
        }
    }

    // 清除数据源名
    public static void clearDB() {
        contextHolder.remove();
    }
}
