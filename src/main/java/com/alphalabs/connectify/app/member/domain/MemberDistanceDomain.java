package com.alphalabs.connectify.app.member.domain;

import com.alphalabs.connectify.app.member.adapter.out.persistence.MemberDistance;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

/**
 * Domain entity 임을 명확하게 하기 위해 suffix로 'Domain'을 사용한다.
 */

@Value
@AllArgsConstructor
@Getter
public class MemberDistanceDomain {
	private final MemberDomain member;
	private final Double distance;
}
