package com.alphalabs.connectify.member.member.application.port.in.command;

import jakarta.validation.constraints.NotEmpty;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = false)
public class RefreshAuthCommand {

	@NotEmpty(message = "Refresh token은 필수 입니다.")
	private final String refreshToken;
}
