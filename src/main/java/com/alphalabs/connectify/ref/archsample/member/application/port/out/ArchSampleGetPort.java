package com.alphalabs.connectify.ref.archsample.member.application.port.out;

import com.alphalabs.connectify.ref.archsample.member.domain.ArchSampleDomain;

import java.util.Optional;

public interface ArchSampleGetPort {

	Optional<ArchSampleDomain> getMember(Long id);
}
