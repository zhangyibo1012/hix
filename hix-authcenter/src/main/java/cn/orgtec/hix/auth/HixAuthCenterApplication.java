package cn.orgtec.hix.auth;

import cn.orgtec.hix.common.security.annotation.EnableHixFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * @author Yibo Zhang
 * @date 2019/09/05
 */
@SpringCloudApplication
@EnableHixFeignClients
public class HixAuthCenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(HixAuthCenterApplication.class, args);
    }
}
