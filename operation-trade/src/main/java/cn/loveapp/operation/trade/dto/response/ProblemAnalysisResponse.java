package cn.loveapp.operation.trade.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author xujianhu
 * @date 2023-05-03 16:17
 * @Description: 题分析结果返回体
 */
@Data
public class ProblemAnalysisResponse {
    /**
     * 分析结果集合
     */
    private List<Param> params;

    @Data
    @AllArgsConstructor
    public static class Param {

        /**
         * 参数名
         */
        private String paramStatusName;

        /**
         * 参数结果
         */
        private String paramStatusValue;

        /**
         * 参数标签
         */
        private Integer paramStatusTab;
    }
}
