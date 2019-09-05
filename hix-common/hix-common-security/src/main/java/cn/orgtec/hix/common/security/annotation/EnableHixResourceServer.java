package cn.orgtec.hix.common.security.annotation;

import cn.orgtec.hix.common.security.component.HixResourceServerAutoConfiguration;
import cn.orgtec.hix.common.security.component.HixSecurityBeanDefinitionRegistrar;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import java.lang.annotation.*;

/**
 * @author lengleng
 * @date 2018/11/10
 * <p>
 * 资源服务注解
 * @EnableResourceServer 资源服务器 让这个项目成为了资源服务器
 * 
 * 接入资源服务器的服务 每次请求需要带着 token 进来 ， 通过 token 获取用户信息 UserDetails 进行权限校验
 * @EnableGlobalMethodSecurity 激活用户对某个控制层的方法是否具有访问权限
 * @PreAuthorize 在方法调用之前, 基于表达式的计算结果来限制对方法的访问
 * @PostAuthorize 允许方法调用, 但是如果表达式计算结果为false, 将抛出一个安全性异常
 * @PostFilter 允许方法调用, 但必须按照表达式来过滤方法的结果
 * @PreFilter 允许方法调用, 但必须在进入方法之前过滤输入值
 *
 */
@Documented
@Inherited
@EnableResourceServer
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Import({HixResourceServerAutoConfiguration.class, HixSecurityBeanDefinitionRegistrar.class})
public @interface EnableHixResourceServer {

}
