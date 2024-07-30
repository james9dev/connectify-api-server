package com.alphalabs.connectify.member.member.application.port.in;

import com.alphalabs.connectify.member.member.application.port.in.command.RefreshAuthCommand;
import com.alphalabs.connectify.member.member.domain.AuthDomain;

public interface AuthUseCase {
	AuthDomain refreshJwt(RefreshAuthCommand command);
}
