package cn.orgtec.hix.config;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author Yibo Zhang
 * @date 2019/09/05
 */
@EnableConfigServer
@SpringCloudApplication
public class HixConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(HixConfigApplication.class, args);
    }
}
