package cn.orgtec.hix.common.security.util;


import cn.hutool.core.util.StrUtil;
import cn.orgtec.hix.common.core.constant.SecurityConstants;
import cn.orgtec.hix.common.security.service.HixUser;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 安全工具类
 *
 * @author L.cm
 */
@UtilityClass
public class SecurityUtils {
	/**
	 * 获取Authentication
	 */
	public Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	/**
	 * 获取用户
	 *
	 * @param authentication
	 * @return HixUser
	 * <p>
	 * 获取当前用户的全部信息 EnablePigxResourceServer true
	 * 获取当前用户的用户名 EnablePigxResourceServer false
	 */
	public HixUser getUser(Authentication authentication) {
		Object principal = authentication.getPrincipal();
		if (principal instanceof HixUser) {
			return (HixUser) principal;
		}
		return null;
	}

	/**
	 * 获取用户
	 */
	public HixUser getUser() {
		Authentication authentication = getAuthentication();
		return getUser(authentication);
	}

	/**
	 * 获取用户角色信息
	 *
	 * @return 角色集合
	 */
	public List<Integer> getRoles() {
		Authentication authentication = getAuthentication();
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

		List<Integer> roleIds = new ArrayList<>();
		authorities.stream()
				.filter(granted -> StrUtil.startWith(granted.getAuthority(), SecurityConstants.ROLE))
				.forEach(granted -> {
					String id = StrUtil.removePrefix(granted.getAuthority(), SecurityConstants.ROLE);
					roleIds.add(Integer.parseInt(id));
				});
		return roleIds;
	}

}
