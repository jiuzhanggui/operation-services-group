package cn.loveapp.operation.trade.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

/**
 * @author xujianhu
 * @date 2023-03-20 11:41
 * @Description: 请求地址配置
 */
@Data
@Configuration
public class RequestAddressConfig {

    @Value("${operation.uac.url:http://172.18.5.187:30018}")
    private String uacUrl;

    /**
     *
     */
    @Value("${operation.uac.getUserFullInfo.path:/export/uac/user/getUserFullInfo}")
    private String uacGetUserFullInfoPath;
}
