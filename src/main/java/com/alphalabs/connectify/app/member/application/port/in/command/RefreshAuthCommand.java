package com.alphalabs.connectify.app.member.application.port.in.command;

import com.alphalabs.connectify.common.SelfValidating;
import jakarta.validation.constraints.NotEmpty;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = false)
public class RefreshAuthCommand extends SelfValidating<GetMemberCommand> {

	@NotEmpty(message = "Refresh token은 필수 입니다.")
	private final String refreshToken;

	public RefreshAuthCommand(String refreshToken) {
		this.refreshToken = refreshToken;

		this.validateSelf();
	}
}
