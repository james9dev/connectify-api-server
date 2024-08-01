package com.alphalabs.connectify.app.member.adapter.out.persistence;


import lombok.Data;

@Data
public class MemberDistance {
	private MemberJpaEntity member;
	private double distance;

	public MemberDistance(MemberJpaEntity member, double distance) {
		this.member = member;
		this.distance = distance;
	}
}