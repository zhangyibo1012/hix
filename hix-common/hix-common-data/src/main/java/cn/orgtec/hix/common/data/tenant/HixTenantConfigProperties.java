package cn.orgtec.hix.common.data.tenant;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * 多租户配置
 *
 * @author oathsign
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "hix.tenant")
public class HixTenantConfigProperties {

	/**
	 * 维护租户列名称
	 */
	private String column="tenant_id";

	/**
	 * 多租户的数据表集合
	 */
	private List<String> tables = new ArrayList<>();
}
