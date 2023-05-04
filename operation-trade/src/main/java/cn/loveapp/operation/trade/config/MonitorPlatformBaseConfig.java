package cn.loveapp.operation.trade.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

/**
 * @author xujianhu
 * @date 2023-05-03 16:10
 * @Description:
 */
@Configuration
@Data
public class MonitorPlatformBaseConfig {
    /**
     * 开通rds的平台
     */
    @Value("${monitor.platform.config.open.rds.platform:TAO,PDD}")
    private List<String> openRdsPlatforms;

    /**
     * 拥有存单功能的vipFlag列表
     */
    @Value("${monitor.platform.allow.open.level.list:1,2}")
    private List<Integer> allowOpenLevelList;

    /**
     * 日志保存天数
     */
    @Value("${monitor.platform.config.log.save.days:7}")
    private Long logSaveDays;

    /**
     * es日志查询的条数
     */
    @Value("${monitor.platform.config.es.search.size:100}")
    private Integer esSearchSize;

    /**
     * es Log地址
     */
    @Value("${monitor.platform.config.es.urls:}")
    private List<String> urls;

    /**
     * 用户名
     */
    @Value("${monitor.platform.config.es.username:}")
    private String username;

    /**
     * 密码
     */
    @Value("${monitor.platform.config.es.password:}")
    private String password;
}
