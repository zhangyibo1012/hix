package cn.orgtec.hix.common.gateway.support;

import cn.orgtec.hix.common.core.constant.CommonConstants;
import cn.orgtec.hix.common.gateway.vo.RouteDefinitionVo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lengleng
 * @date 2018/10/31
 * <p>
 *  Spring Cloud Gateway 默认的 RouteDefinitionWrite 实现类是
 *  InMemoryRouteDefinitionRepository , Route 信息保存在当前实例的内存中，
 *  这在集群环境中会存在同步问题，自定义一个基于 Redis 的
 *  RouteDefinitionWrite。
 *
 *  Redis路由定义存储仓库，优先级比配置文件高
 *  使用Redis保存自定义路由配置（代替默认的InMemoryRouteDefinitionRepository
 *  存在问题：每次请求都会调用getRouteDefinitions，当网关较多时，
 *  会影响请求速度，考虑放到本地Map中，使用消息通知Map更新。
 *
 *  1.spring cloud gateway 基于webflux 背压，暂时不支持mysql 数据库
 *  2.Redis-reactive 支持 spring cloudgateway 的背压，同时还可以实现分布式，高性能
 *  3.upms 模块启动加载数据库中的配置文件到 redis 中.
 *  4.网关模块实现 RouteDefinitionRepository ， getRouteDefinitions() 从 redis 中读取数据
 */
@Slf4j
@Component
@AllArgsConstructor
public class RedisRouteDefinitionWriter implements RouteDefinitionRepository {
	private final RedisTemplate redisTemplate;

	@Override
	public Mono<Void> save(Mono<RouteDefinition> route) {
		return route.flatMap(r -> {
			RouteDefinitionVo vo = new RouteDefinitionVo();
			BeanUtils.copyProperties(r, vo);
			log.info("保存路由信息{}", vo);
			redisTemplate.setKeySerializer(new StringRedisSerializer());
			redisTemplate.opsForHash().put(CommonConstants.ROUTE_KEY, r.getId(), vo);
			return Mono.empty();
		});
	}

	@Override
	public Mono<Void> delete(Mono<String> routeId) {
		routeId.subscribe(id -> {
			log.info("删除路由信息{}", id);
			redisTemplate.setKeySerializer(new StringRedisSerializer());
			redisTemplate.opsForHash().delete(CommonConstants.ROUTE_KEY, id);
		});
		return Mono.empty();
	}


	/**
	 * 动态路由入口
	 *
	 * @return
	 */
	@Override
	public Flux<RouteDefinition> getRouteDefinitions() {
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer<>(RouteDefinitionVo.class));
		List<RouteDefinitionVo> values = redisTemplate.opsForHash().values(CommonConstants.ROUTE_KEY);
		List<RouteDefinition> definitionList = new ArrayList<>();
		values.forEach(vo -> {
			RouteDefinition routeDefinition = new RouteDefinition();
			BeanUtils.copyProperties(vo, routeDefinition);
			definitionList.add(vo);
		});
		log.debug("redis 中路由定义条数： {}， {}", definitionList.size(), definitionList);
		return Flux.fromIterable(definitionList);
	}
}
