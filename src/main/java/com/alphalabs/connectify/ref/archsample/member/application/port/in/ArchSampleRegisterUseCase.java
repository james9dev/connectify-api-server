package com.alphalabs.connectify.ref.archsample.member.application.port.in;

import com.alphalabs.connectify.ref.archsample.member.application.port.in.command.ArchSampleRegisterCommand;

public interface ArchSampleRegisterUseCase {

	Long register(ArchSampleRegisterCommand command);
}
