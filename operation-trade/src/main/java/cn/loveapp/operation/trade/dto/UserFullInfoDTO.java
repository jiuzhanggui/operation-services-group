package cn.loveapp.operation.trade.dto;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * @author xujianhu
 * @date 2023-03-20 10:34
 * @Description: 用户信息数据传输对象
 */
@Data
public class UserFullInfoDTO {
    /**
     * 用户数字ID
     */
    private String sellerId;
    /**
     * 用户昵称
     */
    private String sellerNick;
    /**
     * 用户第一次使用爱用交易的时间
     */
    private LocalDateTime createDate;
    /**
     * 最后一次活动时间，用触发器更新主表
     */
    private LocalDateTime lastactivedt;
    /**
     * 最近一次付款时间
     */
    private LocalDateTime lastPaidTime;
    /**
     * 0-初级班，1-高级版
     */
    private Integer vipflag;
    /**
     * 创建用户时用的IP地址
     */
    private String createipaddress;
    /**
     * 最后一次IP地址
     */
    private String lastipaddress;
    /**
     * TOP 初级版、高级版都有这个字段 订购到期时间
     */
    private LocalDateTime orderCycleEnd;
    /**
     * 授权变更或订购变更时, 上次订购到期时间
     */
    private LocalDateTime lastOrderCycleEnd;

    /**
     * 高级版-加上赠送时间的最终到期时间 初级班-就是TOP到期时间
     */
    private LocalDateTime subdatetime;
    /**
     * TOP的Session
     */
    private String accessToken;
    /**
     * TOP的刷新Key
     */
    private String refreshToken;
    /**
     * 用于灰度发布时控制用户发布区域
     */
    private String roleid;
    /**
     * 0-非默认插件 1-默认插件
     */
    private Integer isdefault;
    /**
     * 本记录最后编辑的时间
     */
    private LocalDateTime lastupdatetime;
    /**
     * 累计PC登陆次数
     */
    private Integer logincountPc;
    /**
     * 累计手机登陆次数
     */
    private Integer logincountMp;
    /**
     * 累计旺旺插件打开测试
     */
    private Integer logincountWw;
    /**
     * 30天内累计PC登陆次数
     */
    private Integer mauPc;
    /**
     * 30天内累计手机登陆次数
     */
    private Integer mauMp;
    /**
     * 30天内累计旺旺插件登陆次数
     */
    private Integer mauWw;
    /**
     * topsession更新时间
     */
    private LocalDateTime topTime;
    /**
     * H5/QAP/PC/PCWW
     */
    private String lastactiveplatform;
    /**
     * 最后活跃的版本号
     */
    private String lastactivever;
    /**
     * 在哪个库
     */
    private String db;
    /**
     * 0不是多店铺，1是多店铺
     */
    private Integer isMany;
    /**
     * 是否是主店铺 0 否，1是主店铺
     */
    private Integer isMain;
    /**
     * 主店铺id
     */
    private String corpId;
    /**
     * tmark打标
     */
    private String mark;
    /**
     * 是否沉默
     */
    private Boolean isSilent;
    /**
     * 是否需要授权字段
     */
    private Integer isNeedauth;
    /**
     * w1授权到期时间
     */
    private LocalDateTime w1Deadline;
    /**
     * 授权变更或订购变更时, 上次订购到期时间(淘宝)
     */
    private LocalDateTime lastW1Deadline;
    /**
     * 授权变更或订购变更时, 上次授权到期时间(多平台)
     */
    private LocalDateTime lastAuthDeadLine;
    /**
     * w2授权到期时间
     */
    private LocalDateTime w2Deadline;
    /**
     * 沉默用户复活时间
     */
    private LocalDateTime revivalDate;
    /**
     * 
     */
    /*===========================1688字段 start==================================*/
    /**
     * 其他角色ID
     */
    private String otherRoleId;
    /**
     * 成员ID
     */
    private String memberId;
    /**
     * 标签
     */
    private String tag;
    /**
     * 供货商id
     */
    private String supplierId;
    /**
     * 供货商nick
     */
    private String supplierNick;
    /*===========================1688字段 end==================================*/

}
