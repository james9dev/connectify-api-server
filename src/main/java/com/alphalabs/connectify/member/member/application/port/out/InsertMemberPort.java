package com.alphalabs.connectify.member.member.application.port.out;

import com.alphalabs.connectify.member.member.domain.KakaoDomain;
import com.alphalabs.connectify.member.member.domain.MemberDomain;

public interface InsertMemberPort {

	Long insertMember(MemberDomain domain);
	Long insertKakaoUser(KakaoDomain domain);
}
