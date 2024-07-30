package com.alphalabs.connectify.app.member.application.port.in.command;

import com.alphalabs.connectify.common.SelfValidating;
import jakarta.validation.constraints.NotEmpty;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = false)
public class RegisterKakaoCommand extends SelfValidating<RegisterKakaoCommand> {

	@NotEmpty(message = "Access token은 필수 입니다.")
	private final String kakaoAccessToken;

	public RegisterKakaoCommand(String accessToken) {
		this.kakaoAccessToken = accessToken;

		this.validateSelf();
	}

}
