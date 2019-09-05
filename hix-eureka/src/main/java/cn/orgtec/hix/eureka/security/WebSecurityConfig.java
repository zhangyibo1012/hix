package cn.orgtec.hix.eureka.security;


import lombok.SneakyThrows;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * WebSecurityConfig
 *
 * @author Yibo Zhang
 * @date 2019/08/06
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	@SneakyThrows
	protected void configure(HttpSecurity http) {
		http.csrf().disable()
				.authorizeRequests()
				.antMatchers("/actuator/**").permitAll()
				.anyRequest()
				.authenticated().and().httpBasic();
	}
}
