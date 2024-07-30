package com.alphalabs.connectify.member.member.adapter.out.persistence;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "member")
@Data
@AllArgsConstructor
@NoArgsConstructor
class MemberJpaEntity {

	@Id
	@GeneratedValue
	private Long no;

	private String email;

	private String name;

	private String phoneNumber;

	private String providerToken;

	private ProviderType providerType;


	public MemberJpaEntity(String email, String name) {
		this.email = email;
		this.name = name;
	}

	public MemberJpaEntity(String email, String providerToken, ProviderType providerType) {
		this.email = email;
		this.providerToken = providerToken;
		this.providerType = providerType;
	}

}