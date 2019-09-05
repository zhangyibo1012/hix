package cn.orgtec.hix.admin;

import cn.orgtec.hix.common.security.annotation.EnableHixFeignClients;
import cn.orgtec.hix.common.security.annotation.EnableHixResourceServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * @author Yibo Zhang
 * @date 2019/09/05
 */
@SpringCloudApplication
@EnableHixFeignClients
@EnableHixResourceServer
public class HixAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(HixAdminApplication.class, args);
    }
}
