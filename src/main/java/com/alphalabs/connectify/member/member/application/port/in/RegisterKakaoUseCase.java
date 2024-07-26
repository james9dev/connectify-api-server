package com.alphalabs.connectify.member.member.application.port.in;

import com.alphalabs.connectify.member.member.application.port.in.command.RegisterKakaoCommand;

public interface RegisterKakaoUseCase {

	Long authKakao(RegisterKakaoCommand command);
}
