package com.alphalabs.connectify.app.member.adapter.out.persistence;

import com.alphalabs.connectify.app.member.domain.enums.GenderType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "profile")
@Data
@AllArgsConstructor
@NoArgsConstructor
class ProfileJpaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nickName;
	private String birthyear;
	private String birthday;

	@Enumerated(EnumType.STRING)
	private GenderType gender;

	private Integer height;

	private Double latitude;
	private Double longitude;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private MemberJpaEntity member;

	@OneToMany(mappedBy = "profile", fetch = FetchType.LAZY)
	private List<PictureJpaEntity> pictures = new ArrayList<>();

}

