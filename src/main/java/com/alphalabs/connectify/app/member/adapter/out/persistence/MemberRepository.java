package com.alphalabs.connectify.app.member.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


interface MemberRepository extends JpaRepository<MemberJpaEntity, Long> {

	//@Query("select m from MemberJpaEntity m where m.providerId = :providerId")
	Optional<MemberJpaEntity> findByProviderId(@Param("providerId") Long providerId);

	@Query("SELECT new com.alphalabs.connectify.app.member.adapter.out.persistence.MemberDistance(m, " +
			"(6371 * acos(cos(radians(:lat)) * cos(radians(m.profile.latitude)) * cos(radians(m.profile.longitude) - radians(:lon)) + sin(radians(:lat)) * sin(radians(m.profile.latitude)))) AS distance) " +
			"FROM MemberJpaEntity m " +
			"WHERE (6371 * acos(cos(radians(:lat)) * cos(radians(m.profile.latitude)) * cos(radians(m.profile.longitude) - radians(:lon)) + sin(radians(:lat)) * sin(radians(m.profile.latitude)))) < :radius " +
			"ORDER BY distance ASC")
	List<MemberDistance> findNearbyMembers(@Param("lat") double lat, @Param("lon") double lon, @Param("radius") double radius);
}