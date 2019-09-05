package cn.orgtec.hix.common.log.event;

import cn.orgtec.hix.admin.api.entity.SysLog;
import cn.orgtec.hix.admin.api.feign.RemoteLogService;
import cn.orgtec.hix.common.core.constant.SecurityConstants;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;

/**
 * @author lengleng
 * 异步监听日志事件
 * 异步的时候没有 token  SecurityConstants.FROM_IN
 */
@Slf4j
@AllArgsConstructor
public class SysLogListener {
	private final RemoteLogService remoteLogService;

	@Async
	@Order
	@EventListener(SysLogEvent.class)
	public void saveSysLog(SysLogEvent event) {
		SysLog sysLog = event.getSysLog();
		remoteLogService.saveLog(sysLog, SecurityConstants.FROM_IN);
	}
}
