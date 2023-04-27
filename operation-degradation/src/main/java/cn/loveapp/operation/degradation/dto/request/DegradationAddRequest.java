package cn.loveapp.operation.degradation.dto.request;

import lombok.Data;

/**
 * @author xujianhu
 * @date 2023-04-26 15:14
 * @Description: 新增降级任务请求体
 */
@Data
public class DegradationAddRequest extends BaseDegradationRequest {

    /**
     * 任务描述
     */
    private String description;

    /**
     * 任务配置接口
     */
    private String configurationInterface;

    /**
     * 项目id（apollo）
     */
    private String projectId;

    /**
     * 操作用户（apollo）
     */
    private String userName;

    /**
     * 集群名称（apollo）
     */
    private String addressName;

    /**
     * 命名空间（默认apollo），mysql对应数据库名，redis对应fild
     */
    private String nameSpace;

    /**
     * 配置参数的Key值（默认apollo），mysql对应CRUD语句，redis对应Key值
     */
    private String key;

    /**
     * 自定义开启参数
     */
    private String openStatusParameter;

    /**
     * 自定义关闭参数
     */
    private String closeStatusParameter;
}
