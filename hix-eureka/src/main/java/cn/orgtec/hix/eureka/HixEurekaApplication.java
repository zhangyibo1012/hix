package cn.orgtec.hix.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 注册中心
 *
 * @author Yibo Zhang
 * @date 2019/08/06
 */
@EnableEurekaServer
@SpringBootApplication
public class HixEurekaApplication {
    public static void main(String[] args) {
        SpringApplication.run(HixEurekaApplication.class, args);
    }
}
