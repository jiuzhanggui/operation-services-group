package com.ruoyi.operation.common.constant;

import java.util.Arrays;
import java.util.List;

/**
 * @author xujianhu
 * @date 2023-03-20 16:20
 * @Description: 通用平台常量
 */
public class CommonPlatformConstants {
    /**
     * 淘宝平台
     */
    public static final String PLATFORM_TAO = "TAO";

    /**
     * 拼多多平台
     */
    public static final String PLATFORM_PDD = "PDD";

    /**
     * 1688平台
     */
    public static final String PLATFORM_1688 = "1688";

    /**
     * 京东平台
     */
    public static final String PLATFORM_JD = "JD";

    /**
     * 快手电商
     */
    public static final String PLATFORM_KWAISHOP = "KWAISHOP";

    /**
     * 抖店
     */
    public static final String PLATFORM_DOUDIAN = "DOUDIAN";

    /**
     * 微信小商店
     */
    public static final String PLATFORM_WXSHOP = "WXSHOP";

    /**
     * 微信视频号小店
     */
    public static final String PLATFORM_WXVIDEOSHOP = "WXVIDEOSHOP";

    /**
     * 小红书
     */
    public static final String PLATFORM_XHS = "XHS";

    /**
     * 必要
     */
    public static final String PLATFORM_BIYAO = "BIYAO";

    /**
     * 有赞
     */
    public static final String PLATFORM_YOUZAN = "YOUZAN";

    /**
     * 爱用
     */
    public static final String PLATFORM_AIYONG = "AIYONG";

    /**
     * 默认
     */
    public static final String PLATFORM_DEFAULT = "DEFAULT";

    public static List<String> getPlatform() {
        return Arrays.asList(PLATFORM_1688, PLATFORM_TAO, PLATFORM_PDD, PLATFORM_JD, PLATFORM_KWAISHOP,
            PLATFORM_DOUDIAN, PLATFORM_WXSHOP, PLATFORM_WXVIDEOSHOP, PLATFORM_XHS, PLATFORM_BIYAO, PLATFORM_YOUZAN,
            PLATFORM_DEFAULT, PLATFORM_AIYONG);
    }
}
