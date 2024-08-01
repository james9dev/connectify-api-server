package com.alphalabs.connectify.app.member.application.port.in;

import com.alphalabs.connectify.app.member.application.port.in.command.UpdateProfileCommand;
import com.alphalabs.connectify.app.member.domain.ProfileDomain;

public interface UpdateProfileUseCase {

	ProfileDomain updateProfile(UpdateProfileCommand updateProfileCommand);
}
