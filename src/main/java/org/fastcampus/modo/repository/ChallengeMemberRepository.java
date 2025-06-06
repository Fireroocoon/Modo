package org.fastcampus.modo.repository;

import org.fastcampus.modo.entity.ChallengeMember;
import org.fastcampus.modo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChallengeMemberRepository extends JpaRepository<ChallengeMember, Long> {
    // Optional: 유저가 이미 참여했는지 검사
    boolean existsByUserIdxAndChallenge_ChallengeId(Long userIdx, Long challengeId);

    boolean existsByChallenge_ChallengeIdAndUser_Idx(Integer challengeId, Integer userIdx);

    void deleteByChallenge_ChallengeId(Integer challengeId);

    List<ChallengeMember> findByUser_Idx(Integer userId);

    List<ChallengeMember> findByUser(User user);
}