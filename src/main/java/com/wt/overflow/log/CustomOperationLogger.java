package com.wt.overflow.log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.wt.overflow.bean.OperationLog;
import com.wt.overflow.dao.OperationLogMapper;
import com.wt.overflow.util.Tools;
import com.wt.overflow.util.UUIDUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 用于记录自定义 系统日志
 */
public class CustomOperationLogger implements ApplicationContextAware {

    /**
     * 日志队列
     */
    private static final ConcurrentLinkedQueue<OperationLog> logs = new ConcurrentLinkedQueue<OperationLog>();


    private static CustomOperationLogger customOperationLogger = null;

    /*
     * 获取到的bean的必须是接口类型
     */
    private static ApplicationContext context = null;

    /**
     * 操作日志记录对象
     */
    private static OperationLogMapper operationLogMapper = null;

    /**
     * 用于执行 log 记录线程
     */
    private static Thread logThread = null;

    private static final AtomicBoolean isRun = new AtomicBoolean(true);

    protected Logger logger = LoggerFactory.getLogger(getClass());

    private CustomOperationLogger() {
    }

    /**
     * 获取记录对象
     * @return
     */
    public static CustomOperationLogger getLogger() {
        if (customOperationLogger == null) {
            customOperationLogger = new CustomOperationLogger();
        }
        return customOperationLogger;
    }

    private static final List<OperationLog> runOperationLog = new ArrayList<>();

    /**
     * 初始化日志记录线程
     * @throws Exception
     */
    private void initLogger() throws Exception {
        operationLogMapper = context.getBean(OperationLogMapper.class);
        if (operationLogMapper == null) {
            throw new RuntimeException("未从 spring 容器中获取到 OperationLogMapper 类型Bean...");
        }
        if (logThread == null) {
            logThread = new Thread(() -> {
                while (true) {
                    try {
                        if (logs.isEmpty()) {
                            synchronized (isRun) {
                                if (logs.isEmpty()) {
                                    isRun.set(false);
                                    isRun.wait();
                                    continue;
                                }
                            }
                        }
                        // 处理记录 每次最多处理 50 条
                        for (int i = 0; i < 50; i++) {
                            OperationLog logInstance = logs.poll();
                            if (logInstance == null) {
                                break;
                            }
                            runOperationLog.add(logInstance);
                        }
                        if (!runOperationLog.isEmpty()) {
                            Tools.splitBatch(runOperationLog, 10, list -> {
                                try {
                                    // System.err.println(String.format("模拟记录插入条数:%s 插入数据:%s", runLogInstances.size(), JSONUtils.toJSONString(list)));
                                    operationLogMapper.adds(list);
                                } catch (Exception e) {
                                    logger.info("记录系统日志发生错误! 错误信息:%s 记录数据:%s",
                                            e.getMessage(),toJSONStringDefult(list));
                                }
                            });
                        }
                        runOperationLog.clear();
                        TimeUnit.SECONDS.sleep(logs.size() > 200 ? 1 : 5);
                    } catch (Exception e) {
                        logger.info("自定义系统日志记录线程执行发生异常! 信息:%s",e.getMessage());
                    }
                }
            });
            logThread.start();
        }
    }

    /**
     * 默认标准转换方式.<br/>
     * 不输出为null的字段
     * 判断如果输出字符串 .isNull() 转换失败!
     * @param object 实体
     * @return
     */
    public String toJSONStringDefult(Object object) {
        if (object == null) {
            return "";
        }
        try {
            return JSON.toJSONString(object,
                    //取消循环引用
                    SerializerFeature.DisableCircularReferenceDetect,
                    //按字段名称排序后输出
                    SerializerFeature.SortField,
                    //如果key不是String,转化成String,防止js无法解析.
                    SerializerFeature.WriteNonStringKeyAsString);
        } catch (Exception e) {
            logger.info("toJSONStringDefult 发生失败! 信息:" + e.getMessage());
        }
        return "";
    }

    /**
     * 添加日志记录
     * @param log
     */
    public void log(OperationLog log) {
        if (logThread == null) {
            logger.info(String.format("此条日志记录失败! 记录线程未初始化... log:%s", log));
        }
        if (!logs.offer(log)) {
            logger.info(String.format("此条日志记录失败! log:%s", log));
        }
        if (!isRun.get()) {
            synchronized (isRun) {
                isRun.set(true);
                isRun.notify();
            }
        }
    }

    /**
     * <p>批量添加记录</p>
     *
     * @param logs
     */
    public void logs(List<OperationLog> logs) {
        if (logThread == null) {
            logger.info(String.format("此些日志记录失败! 记录线程未初始化... logs:%s", toJSONStringDefult(logs)));
        }
        try {
            if (!CustomOperationLogger.logs.addAll(logs)) {
                logger.info(String.format("此些日志记录失败! logs:%s", toJSONStringDefult(logs)));
            }
        } catch (Exception e) {
            logger.info(String.format("此些日志记录失败! 信息:%s; logs:%s", logs));
        }
        if (!isRun.get()) {
            synchronized (isRun) {
                isRun.set(true);
                isRun.notify();
            }
        }
    }

    /**
     * 添加日志记录
     * @param value 接口名称
     * @param notes 接口说明
     * @param nickname 模块别名
     * @param sysUserId 登录用户id
     */
    public void log(String value, String notes, String nickname,String sysUserId) {
        OperationLog log = new OperationLog();
        log.setFlowId(UUIDUtil.getUUID());
        log.setCreateTime(new Date());
        log.setFunctionValue(value);
        log.setNotes(notes);
        log.setNickname(nickname);
        log.setOperateUser(sysUserId);
        log.setResult("接口调用结果");
        // 执行记录
        log(log);
    }


    /**
     * 用于获取 spring 容器对象
     * @param applicationContext
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        //这里spring容器初始化时会注入容器对象，在这里做功能初始化
        context = applicationContext;
        try {
            initLogger();
        } catch (Exception e) {
            logger.info(String.format("初始化 自定义系统日志记录工具包失败! 信息:%s", e.getMessage()));
        }
    }

}
