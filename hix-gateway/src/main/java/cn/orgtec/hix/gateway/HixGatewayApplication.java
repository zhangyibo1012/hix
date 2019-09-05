package cn.orgtec.hix.gateway;

import cn.orgtec.hix.common.gateway.annotation.EnableHixDynamicRoute;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * @author Yibo Zhang
 * @date 2019/09/05
 */
@EnableHixDynamicRoute
@SpringCloudApplication
public class HixGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(HixGatewayApplication.class, args);
    }
}
