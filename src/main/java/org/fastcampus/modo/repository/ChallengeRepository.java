package org.fastcampus.modo.repository;

import org.fastcampus.modo.entity.Challenge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChallengeRepository extends JpaRepository<Challenge, Long> {
    // 필요하면 custom method 추가 가능
}