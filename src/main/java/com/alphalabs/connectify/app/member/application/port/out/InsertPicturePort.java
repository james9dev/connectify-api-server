package com.alphalabs.connectify.app.member.application.port.out;

import com.alphalabs.connectify.app.member.domain.ProfileDomain;

public interface InsertPicturePort {
	ProfileDomain.ProfilePicture insertPicture(Long memberId, String filePath, Integer order);
}
