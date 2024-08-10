package com.alphalabs.connectify.app.member.adapter.out.persistence;

import com.alphalabs.connectify.app.member.domain.enums.ProviderType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "member")
@Data
@AllArgsConstructor
@NoArgsConstructor
class MemberJpaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String email;

	private String name;

	private String phoneNumber;

	@ColumnDefault("false")
	private Boolean verifiedEmail;

	@ColumnDefault("false")
	private Boolean verifiedPhoneNumber;

	@Enumerated(EnumType.STRING)
	private ProviderType providerType;
	@Column(unique = true)
	private Long providerId;
	private String providerToken;

	@OneToOne(mappedBy = "member")
	private ProfileJpaEntity profile;

	public MemberJpaEntity(String email,
						   boolean verifiedEmail,
						   String phoneNumber,
						   boolean verifiedPhoneNumber,
						   ProviderType providerType,
						   Long providerId,
						   String providerToken) {
		this.email = email;
		this.verifiedEmail = verifiedEmail;

		this.phoneNumber = phoneNumber;
		this.verifiedPhoneNumber = verifiedPhoneNumber;

		this.providerType = providerType;
		this.providerId = providerId;
		this.providerToken = providerToken;

	}
}