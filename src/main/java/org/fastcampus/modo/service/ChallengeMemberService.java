package org.fastcampus.modo.service;

import org.fastcampus.modo.entity.Challenge;
import org.fastcampus.modo.entity.ChallengeMember;
import org.fastcampus.modo.entity.User;
import org.fastcampus.modo.repository.ChallengeMemberRepository;
import org.fastcampus.modo.repository.ChallengeRepository;
import org.fastcampus.modo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ChallengeMemberService {

    private final ChallengeMemberRepository challengeMemberRepository;
    private final ChallengeRepository challengeRepository;
    private final UserRepository userRepository;

    @Autowired
    public ChallengeMemberService(ChallengeMemberRepository challengeMemberRepository,
                                  ChallengeRepository challengeRepository,
                                  UserRepository userRepository) {
        this.challengeMemberRepository = challengeMemberRepository;
        this.challengeRepository = challengeRepository;
        this.userRepository = userRepository;
    }

    public ChallengeMember joinChallenge(Integer challengeId, Integer userIdx) {
        if (challengeMemberRepository.existsByChallengeIdAndUserIdx(challengeId, userIdx)) {
            throw new IllegalStateException("이미 참여한 챌린지입니다.");
        }

        Challenge challenge = challengeRepository.findById(Long.valueOf(challengeId))
                .orElseThrow(() -> new IllegalArgumentException("챌린지를 찾을 수 없습니다."));
        User user = userRepository.findById(Long.valueOf(userIdx))
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        ChallengeMember member = new ChallengeMember();
        member.setChallenge(challenge);
        member.setUser(user);
        member.setIsAccepted(true); // 기본값 true
        member.setJoinedAt(LocalDateTime.now());

        return challengeMemberRepository.save(member);
    }
}