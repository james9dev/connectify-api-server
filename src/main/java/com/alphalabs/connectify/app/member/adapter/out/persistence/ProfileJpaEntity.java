package com.alphalabs.connectify.app.member.adapter.out.persistence;

import com.alphalabs.connectify.app.member.domain.enums.GenderType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.bind.DefaultValue;

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

}

