/*
 *
 *      Copyright (c) 2018-2025, lengleng All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 *  this list of conditions and the following disclaimer.
 *  Redistributions in binary form must reproduce the above copyright
 *  notice, this list of conditions and the following disclaimer in the
 *  documentation and/or other materials provided with the distribution.
 *  Neither the name of the pig4cloud.com developer nor the names of its
 *  contributors may be used to endorse or promote products derived from
 *  this software without specific prior written permission.
 *  Author: lengleng (wangiegie@gmail.com)
 *
 */

package cn.orgtec.hix.gateway.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

/**
 * @author lengleng
 * @date 2018/7/1
 * 路由限流配置
 *
 * 可以通过 KeyResolver 来指定限流的 key ，
 */
@Configuration
public class RateLimiterConfiguration {

	/**
	 *  根据 IP 来做限流
	 * @return  KeyResolver
	 */
	@Bean(value = "remoteAddrKeyResolver")
	public KeyResolver remoteAddrKeyResolver() {

		/**
		 *  通过 exchange 对象获取到请求信息
		 *  [{"args": {"key-resolver": "#{@remoteAddrKeyResolver}", "redis-rate-limiter.burstCapacity": "20", "redis-rate-limiter.replenishRate": "10"}, "name": "RequestRateLimiter"}, {"args": {"name": "default", "fallbackUri": "forward:/fallback"}, "name": "Hystrix"}]
		 *
		 * filter 名称必须是 RequestRateLimiter
		 * redis-rate-limiter.replenishRate :允许用户每秒处理多少请求
		 * redis-rate-limiter.burstCapacity: 令牌桶容量 允许在1秒内完成的最大
		 * 									 请求。
		 * key-resolver：使用SpEL按名称引用bean
		 *
		 */
		return exchange -> Mono.just(exchange.getRequest().getRemoteAddress().getAddress().getHostAddress());
	}
}
