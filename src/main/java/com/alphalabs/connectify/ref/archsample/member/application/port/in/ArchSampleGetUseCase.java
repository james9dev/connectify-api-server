package com.alphalabs.connectify.ref.archsample.member.application.port.in;

import com.alphalabs.connectify.ref.archsample.member.domain.ArchSampleDomain;

public interface ArchSampleGetUseCase {

	ArchSampleDomain getMember(Long id);
}
