package cn.loveapp.operation.degradation.entity;

import java.lang.reflect.Type;
import java.time.LocalDateTime;

import com.alibaba.fastjson.JSON;

import cn.loveapp.operation.degradation.dto.request.DegradationAddRequest;
import lombok.Data;

/**
 * @author xujianhu
 * @date 2023-04-26 15:12
 * @Description: 降级任务方案表(DegradationTask)实体类
 */
@Data
public class DegradationTask {

    private Integer id;
    /**
     * 任务名
     */
    private String name;
    /**
     * 任务描述
     */
    private String description;
    /**
     * 配置参数。
     */
    private String configurationParameter;
    /**
     * 自定义任务开始时间
     */
    private LocalDateTime startTime;
    /**
     * 自定义任务结束时间
     */
    private LocalDateTime endTime;
    /**
     * 降级开关状态。0：任务处于关闭状态，1：降级任务处于开启状态开启（在降级成功和定时降级执行中都属于开启状态）
     */
    private Integer switchStatus;
    /**
     * 定时器执行状态。0：无定时任务，1：定时任务已执行，3：定时任务已关闭
     */
    private Integer timerStatus;

    @Data
    public static class DegradationTaskParameter {

        /**
         * 配置接口：redis、apollo、mysql 参数封装实体类
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

        /*
         *集群名称（apollo）
         * */
        private String addressName;

        /**
         * 命名空间（apollo）、对应mysql的表名、redis的field值
         */
        private String nameSpace;

        /**
         * 被修改的配置Key值（apollo）对应mysql的sql与、redis的Key值
         */
        private String key;
        /**
         * 开启状态参数。true、on
         */
        private String openStatusParameter;
        /**
         * 关闭状态参数。false、off
         */
        private String closeStatusParameter;

        public static DegradationTaskParameter of(DegradationAddRequest degradationAddRequest) {
            DegradationTask.DegradationTaskParameter degradationTaskParameter =
                new DegradationTask.DegradationTaskParameter();
            degradationTaskParameter.setConfigurationInterface(degradationAddRequest.getConfigurationInterface());
            degradationTaskParameter.setOpenStatusParameter(degradationAddRequest.getOpenStatusParameter());
            degradationTaskParameter.setCloseStatusParameter(degradationAddRequest.getCloseStatusParameter());
            degradationTaskParameter.setProjectId(degradationAddRequest.getProjectId());
            degradationTaskParameter.setUserName(degradationAddRequest.getUserName());
            degradationTaskParameter.setAddressName(degradationAddRequest.getAddressName());
            degradationTaskParameter.setNameSpace(degradationAddRequest.getNameSpace());
            degradationTaskParameter.setKey(degradationAddRequest.getKey());
            return degradationTaskParameter;
        }
    }

    /**
     * JSON序列化工具
     *
     * @param configurationParameter
     * @return
     */
    public DegradationTaskParameter getDDegradationTaskConfig(String configurationParameter) {
        return JSON.parseObject(configurationParameter, (Type)DegradationTaskParameter.class);
    }
}
