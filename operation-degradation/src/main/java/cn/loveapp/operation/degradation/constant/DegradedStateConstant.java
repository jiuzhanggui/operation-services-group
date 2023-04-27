package cn.loveapp.operation.degradation.constant;

/**
 * @author xujianhu
 * @date 2023-04-26 15:35
 * @Description: 降级任务相关参数说明
 */
public class DegradedStateConstant {
    /**
     * 降级开关-关闭状态
     */
    public static final Integer DEGRADATION_SWITCH_OFF = 0;
    /**
     * 降级开关-打开状态
     */
    public static final Integer DEGRADATION_SWITCH_ON = 1;
    /**
     * 定时器状态-无定时任务
     */
    public static final Integer TIMER_STATUS_NULL = 0;
    /**
     * 定时器状态-定时任务已启动
     */
    public static final Integer TIMER_STATUS_OPEN = 1;
    /**
     * 降级状态-定时任务准备中
     */
    public static final Integer TIMER_STATUS_WAIT = 2;
    /**
     * 配置加载地址-redis1
     */
    public static final String CONFIGURATION_INTERFACE_USER_REDIS = "userRedis";
    /**
     * 配置加载地址-redis2
     */
    public static final String CONFIGURATION_INTERFACE_STRING_REDIS = "stringRedis";
    /**
     * 配置加载地址-apollo
     */
    public static final String CONFIGURATION_INTERFACE_APOLLO = "apollo";
    /**
     * 配置加载地址-mysql
     */
    public static final String CONFIGURATION_INTERFACE_MYSQL = "mysql";
}
