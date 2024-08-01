package com.alphalabs.connectify.app.member.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

interface ProfileRepository extends JpaRepository<ProfileJpaEntity, Long> {
}