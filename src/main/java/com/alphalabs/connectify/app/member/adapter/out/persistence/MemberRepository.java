package com.alphalabs.connectify.app.member.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


interface MemberRepository extends JpaRepository<MemberJpaEntity, Long> {

	@Query("select m from MemberJpaEntity m where m.providerToken = :providerToken")
	Optional<MemberJpaEntity> findByProviderToken(@Param("providerToken") String providerToken);
}
