package com.alphalabs.connectify.ref.archsample.member.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "member")
@Data
@AllArgsConstructor
@NoArgsConstructor
class ArchSampleJpaEntity {

	@Id
	@GeneratedValue
	private Long id;

	@Column
	private String email;

	@Column
	private String password;

	@Column
	private String name;

}
