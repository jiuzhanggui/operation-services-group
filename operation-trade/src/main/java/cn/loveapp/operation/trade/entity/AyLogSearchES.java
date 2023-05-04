package cn.loveapp.operation.trade.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Mapping;

import java.util.Date;

/**
 * @author xujianhu
 * @date 2023-05-03 16:07
 * @Description:
 */
@Data
@Mapping
public class AyLogSearchES {
    /**
     * id
     */
    @Id
    @Field(type = FieldType.Keyword)
    private String id;

    /**
     * 卖家昵称
     */
    private String sellerNick;

    /**
     * 应用名称
     */
    @JSONField(name = "app.name")
    private String appName;

    /**
     * 链路id
     */
    private String traceId;

    /**
     * 日志时间
     */
    @JSONField(name = "@timestamp", format = "yyyy-MM-dd HH:mm:ss")
    private Date timestamp;

    /**
     * 日志标准
     */
    private String level;

    /**
     * 日志消息体
     */
    private String message;

    /**
     * 日志消息id
     */
    private String messageId;

    /**
     * 日志地址
     */
    private String host;

    /**
     * 最近一次修改时间
     */
    private String modified;
}
