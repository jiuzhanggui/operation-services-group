package cn.loveapp.operation.trade.dto;

import java.time.LocalDateTime;

import com.ruoyi.common.utils.bean.BeanUtils;

import cn.loveapp.operation.trade.entity.OrderSearchTrade;
import lombok.Data;

/**
 * @author xujianhu
 * @date 2023-03-21 11:25
 * @Description: 订购记录基本信息数据传输对象
 */
@Data
public class OrderSearchTradeInfoDTO {

    /**
     * 用户数字ID
     */
    private String userId;
    /**
     * 用户昵称
     */
    private String nick;
    /**
     * 应用名称
     */
    private String articleName;
    /**
     * 应用收费代码，从合作伙伴后台（my.open.taobao.com）-收费管理-收费项目列表 能够获得该应用的收费代码
     */
    private String articleCode;
    /**
     * 商品模型名称
     */
    private String articleItemName;
    /**
     * 收费项目代码，从合作伙伴后台（my.open.taobao.com）-收费管理-收费项目列表 能够获得收费项目代码
     */
    private String itemCode;
    /**
     * 订购周期 1表示年 ，2表示月，3表示天。
     */
    private String orderCycle;
    /**
     * 订单创建时间（订购时间）
     */
    private LocalDateTime createDate;
    /**
     * 订购周期开始时间
     */
    private LocalDateTime orderCycleStart;
    /**
     * 订购周期结束时间
     */
    private LocalDateTime orderCycleEnd;
    /**
     * 订单类型，1=新订 2=续订 3=升级 4=后台赠送 5=后台自动续订 6=订单审核后生成订购关系（暂时用不到）
     */
    private Integer bizType;
    /**
     * 原价（单位为分）
     */
    private Integer fee;
    /**
     * 优惠（单位为分）
     */
    private Integer promFee;
    /**
     * 退款（单位为分；升级时，系统会将升级前老版本按照剩余订购天数退还剩余金额）
     */
    private Integer refundFee;
    /**
     * 实付（单位为分）
     */
    private Integer totalPayFee;
    /**
     * 子订单号
     */
    private String orderId;
    /**
     * 订单号
     */
    private String bizOrderId;
    /**
     * 营销代码
     */
    private String activityCode;
    /**
     * 收费项目名称
     */
    private String itemName;
    /**
     * 服务到期时间，大于等于order_cycle_end
     */
    private LocalDateTime maturitydt;
    /**
     * appkey
     */
    private String appkey;
    /**
     * 事件名
     */
    private String eventName;
    /**
     * 来自(PC,WW,Mobile,QAP)
     */
    private String from;
    /**
     * 推广位
     */
    private String extension;
    /**
     * 创意
     */
    private String originality;
    /**
     * 是否为活动:1是0否;为1时，event_name存放活动名
     */
    private String hasAct;
    /**
     * 170101
     */
    private String day;
    /**
     * 创意id
     */
    private Integer openCid;
    /**
     * 店铺订购前30天的订单数
     */
    private Integer ordersLast30Days;
    /**
     * ios/android/pc
     */
    private String tjplatform;
    /**
     * 一级分类 续费/活动/功能点/升级/客服售卖（KF）/交易引流/服务市场
     */
    private String primaryClass;
    /**
     * 二级分类 详细续费类型、详细功能点
     */
    private String secondaryClass;

    private String subnick;

    private Integer isSubuserOrder;

    /**
     * 将数据库查出来的数据转化为传输数据
     *
     * @param orderSearchTrade
     * @return
     */
    public static OrderSearchTradeInfoDTO of(OrderSearchTrade orderSearchTrade) {
        OrderSearchTradeInfoDTO orderSearchTradeInfoDto = new OrderSearchTradeInfoDTO();
        BeanUtils.copyProperties(orderSearchTrade, orderSearchTradeInfoDto);
        return orderSearchTradeInfoDto;
    }
}
