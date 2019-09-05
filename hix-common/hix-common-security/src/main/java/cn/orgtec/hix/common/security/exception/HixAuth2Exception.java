package cn.orgtec.hix.common.security.exception;

import cn.orgtec.hix.common.security.component.HixAuth2ExceptionSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * @author lengleng
 * @date 2018/7/8
 * 自定义OAuth2Exception
 */
@JsonSerialize(using = HixAuth2ExceptionSerializer.class)
public class HixAuth2Exception extends OAuth2Exception {
	@Getter
	private String errorCode;

	public HixAuth2Exception(String msg) {
		super(msg);
	}

	public HixAuth2Exception(String msg, String errorCode) {
		super(msg);
		this.errorCode = errorCode;
	}
}
