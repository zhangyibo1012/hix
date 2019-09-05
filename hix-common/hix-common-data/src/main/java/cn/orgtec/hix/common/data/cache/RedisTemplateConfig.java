/*
 *    Copyright (c) 2018-2025, lengleng All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the pig4cloud.com developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: lengleng (wangiegie@gmail.com)
 */

package cn.orgtec.hix.common.data.cache;

import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * RedisTemplate  配置
 *
 * @author L.cm
 */
@EnableCaching
@Configuration
@ConditionalOnBean(RedisConnectionFactory.class)
@AllArgsConstructor
public class RedisTemplateConfig {
	private final RedisConnectionFactory redisConnectionFactory;

	/**
	 * redis 写入磁盘的数据实际上都是以字节（0101这样的二进制数据）的形式写入的.
	 *
	 *  常用的配置为键采用
	 *  StringRedisSerializer来序列化，
	 *  value采用默认的JdkSerializationSerializer。
	 *
	 *  序列化 String ——> Byte
	 *  反序列化 Byte ——> Byte
	 *
	 * 	当使用 StringRedisSerializer 做 Value 的序列化时，StringRedisSerializer
	 * 	的泛型指定的是 String ，传入其它类型转换错误。
	 *
	 *  使用 JDK 提供的序列化功能， JdkSerializationRedisSerializer 优点是反序列
	 *  化时不需要提供类的信息 class ，序列化对象 pojo 必须实现 Serializable
	 *  接口。
	 *
	 * @return
	 */
	@Bean
	public RedisTemplate<String, Object> pigxRedisTemplate() {
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setHashKeySerializer(new StringRedisSerializer());
//		redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
		redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
		redisTemplate.setHashValueSerializer(new JdkSerializationRedisSerializer());
		redisTemplate.setConnectionFactory(redisConnectionFactory);
		return redisTemplate;
	}
}
