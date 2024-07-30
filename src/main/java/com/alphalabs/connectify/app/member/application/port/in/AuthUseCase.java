package com.alphalabs.connectify.app.member.application.port.in;

import com.alphalabs.connectify.app.member.application.port.in.command.RefreshAuthCommand;
import com.alphalabs.connectify.app.member.domain.AuthDomain;

public interface AuthUseCase {
	AuthDomain refreshJwt(RefreshAuthCommand command);
}
