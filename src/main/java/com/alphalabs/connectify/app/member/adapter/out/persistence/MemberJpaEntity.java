package com.alphalabs.connectify.app.member.adapter.out.persistence;

import com.alphalabs.connectify.app.member.domain.enums.ProviderType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "member")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
class MemberJpaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@CreatedDate
	private LocalDateTime createdAt;

	private String name;

	private String email;
	@ColumnDefault("false")
	private Boolean verifiedEmail;

	private String phoneNumber;

	@ColumnDefault("false")
	private Boolean verifiedPhoneNumber;

	@Enumerated(EnumType.STRING)
	private ProviderType providerType;
	@Column(unique = true)
	private Long providerId;
	private String providerToken;

	@OneToOne(mappedBy = "member")
	private ProfileJpaEntity profile;

	public MemberJpaEntity(String name,
						   String email,
						   boolean verifiedEmail,
						   String phoneNumber,
						   boolean verifiedPhoneNumber,
						   ProviderType providerType,
						   Long providerId,
						   String providerToken) {
		this.name = name;
		this.email = email;
		this.verifiedEmail = verifiedEmail;

		this.phoneNumber = phoneNumber;
		this.verifiedPhoneNumber = verifiedPhoneNumber;

		this.providerType = providerType;
		this.providerId = providerId;
		this.providerToken = providerToken;

	}
}