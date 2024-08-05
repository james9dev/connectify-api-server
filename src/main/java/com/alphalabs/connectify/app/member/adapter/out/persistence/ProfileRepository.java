package com.alphalabs.connectify.app.member.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

interface ProfileRepository extends JpaRepository<ProfileJpaEntity, Long> {
	ProfileJpaEntity findByMemberId(@Param("member_id") Long memberId);
}