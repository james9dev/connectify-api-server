package com.alphalabs.connectify.member.member.application.port.in;

import com.alphalabs.connectify.member.member.application.port.in.command.RegisterKakaoCommand;
import com.alphalabs.connectify.member.member.domain.AuthDomain;

public interface RegisterKakaoUseCase {

	AuthDomain authKakao(RegisterKakaoCommand command);
}
