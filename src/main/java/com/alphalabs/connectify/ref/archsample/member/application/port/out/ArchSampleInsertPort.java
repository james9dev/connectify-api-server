package com.alphalabs.connectify.ref.archsample.member.application.port.out;

import com.alphalabs.connectify.ref.archsample.member.application.port.out.input.ArchSampleInsertDto;
import com.alphalabs.connectify.ref.archsample.member.domain.ArchSampleDomain;

public interface ArchSampleInsertPort {

	Long insertMember(ArchSampleInsertDto sampleDomain);
}
