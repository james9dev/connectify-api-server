package com.alphalabs.connectify.app.member.application.port.in;

import com.alphalabs.connectify.app.member.application.port.in.command.RegisterKakaoCommand;
import com.alphalabs.connectify.app.member.domain.AuthDomain;

public interface RegisterKakaoUseCase {

	AuthDomain authKakao(RegisterKakaoCommand command);
}
