package com.alphalabs.connectify.app.member.application.port.out;

import com.alphalabs.connectify.app.member.domain.KakaoDomain;
import com.alphalabs.connectify.app.member.domain.MemberDomain;

public interface InsertMemberPort {

	Long insertMember(MemberDomain domain);
	Long insertKakaoUser(KakaoDomain domain);
}
