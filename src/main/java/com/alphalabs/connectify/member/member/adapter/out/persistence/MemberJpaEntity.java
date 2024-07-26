package com.alphalabs.connectify.member.member.adapter.out.persistence;

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
	private Long id;

	private String email;

	private String name;

	private String providerToken;

	private ProviderType providerType;

}
