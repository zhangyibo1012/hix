package cn.orgtec.hix.common.data.tenant;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lengleng
 * @date 2018/9/14
 * feign 租户信息拦截
 */
@Configuration
public class HixFeignTenantConfiguration {
	@Bean
	public RequestInterceptor pigxFeignTenantInterceptor() {
		return new HixFeignTenantInterceptor();
	}
}
