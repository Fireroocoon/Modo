package org.fastcampus.modo.service;

import org.fastcampus.modo.dto.ChallengeBasicInfoDTO;
import org.fastcampus.modo.dto.ChallengeTitleDescriptionDTO;
import org.fastcampus.modo.dto.UserChallengeSummaryDTO;
import org.fastcampus.modo.entity.Challenge;
import org.fastcampus.modo.entity.ChallengeMember;
import org.fastcampus.modo.repository.ChallengeMemberRepository;
import org.fastcampus.modo.repository.ChallengeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ChallengeService {

    private final ChallengeRepository challengeRepository;
    ChallengeMemberRepository challengeMemberRepository;

    @Autowired
    public ChallengeService(ChallengeRepository challengeRepository) {
        this.challengeRepository = challengeRepository;
    }

    public Challenge markChallengeCompleted(Integer challengeId) {
        Challenge challenge = getChallengeOrThrow(challengeId);
        challenge.setIsCompleted(true);
        return challengeRepository.save(challenge);
    }

    public Challenge cancelChallengeCompleted(Integer challengeId) {
        Challenge challenge = getChallengeOrThrow(challengeId);
        challenge.setIsCompleted(false);
        return challengeRepository.save(challenge);
    }

    private Challenge getChallengeOrThrow(Integer challengeId) {
        return challengeRepository.findById(Long.valueOf(challengeId))
                .orElseThrow(() -> new IllegalArgumentException("챌린지를 찾을 수 없습니다."));
    }

    public void deleteChallenge(Integer challengeId) {
        // 연관된 챌린지 멤버 먼저 삭제
        challengeMemberRepository.deleteByChallenge_ChallengeId(challengeId);

        // 챌린지 삭제
        Challenge challenge = challengeRepository.findById(Long.valueOf(challengeId))
                .orElseThrow(() -> new IllegalArgumentException("챌린지를 찾을 수 없습니다."));
        challengeRepository.delete(challenge);
    }

    public ChallengeTitleDescriptionDTO getChallengeInfo(Integer challengeId) {
        Challenge challenge = challengeRepository.findById(Long.valueOf(challengeId))
                .orElseThrow(() -> new IllegalArgumentException("챌린지를 찾을 수 없습니다."));
        return new ChallengeTitleDescriptionDTO(challenge.getTitle(), challenge.getDescription());
    }

    public List<UserChallengeSummaryDTO> getUserChallengeSummaries(Integer userId) {
        List<ChallengeMember> memberList = challengeMemberRepository.findByUser_Idx(userId);

        // 챌린지별 참가 인원 수를 계산하기 위해 Map 사용
        Map<Integer, Long> challengeParticipantCounts = memberList.stream()
                .map(ChallengeMember::getChallenge)
                .distinct()
                .collect(Collectors.toMap(
                        Challenge::getChallengeId,
                        c -> c.getChallengeMembers().stream().count()
                ));

        return memberList.stream()
                .map(ChallengeMember::getChallenge)
                .distinct()
                .map(challenge -> new UserChallengeSummaryDTO(
                        challenge.getCategory(),
                        challenge.getTitle(),
                        challengeParticipantCounts.getOrDefault(challenge.getChallengeId(), 0L).intValue()
                ))
                .collect(Collectors.toList());
    }

    public ChallengeBasicInfoDTO getChallengeBasicInfo(Integer challengeId) {
        Challenge challenge = challengeRepository.findById(Long.valueOf(challengeId))
                .orElseThrow(() -> new RuntimeException("Challenge not found with ID: " + challengeId));

        return new ChallengeBasicInfoDTO(
                challenge.getCategory(),
                challenge.getTitle(),
                challenge.getColor()
        );
    }
}