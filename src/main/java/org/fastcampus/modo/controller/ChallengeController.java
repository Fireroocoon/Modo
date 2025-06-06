package org.fastcampus.modo.controller;

import org.fastcampus.modo.dto.ChallengeBasicInfoDTO;
import org.fastcampus.modo.dto.ChallengeTitleDescriptionDTO;
import org.fastcampus.modo.dto.UserChallengeSummaryDTO;
import org.fastcampus.modo.entity.Challenge;
import org.fastcampus.modo.service.ChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/challenges")
public class ChallengeController {

    private final ChallengeService challengeService;

    @Autowired
    public ChallengeController(ChallengeService challengeService) {
        this.challengeService = challengeService;
    }

    // 챌린지 완료 처리
    @PostMapping("/{challengeId}/complete")
    public ResponseEntity<Challenge> completeChallenge(@PathVariable Integer challengeId) {
        Challenge updated = challengeService.markChallengeCompleted(challengeId);
        return ResponseEntity.ok(updated);
    }

    // 챌린지 완료 취소
    @PostMapping("/{challengeId}/cancel-complete")
    public ResponseEntity<Challenge> cancelCompleteChallenge(@PathVariable Integer challengeId) {
        Challenge updated = challengeService.cancelChallengeCompleted(challengeId);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{challengeId}")
    public ResponseEntity<String> deleteChallenge(@PathVariable Integer challengeId) {
        challengeService.deleteChallenge(challengeId);
        return ResponseEntity.ok("챌린지 및 관련 참가 정보가 삭제되었습니다.");
    }

    @GetMapping("/{challengeId}/info")
    public ResponseEntity<ChallengeTitleDescriptionDTO> getChallengeInfo(@PathVariable Integer challengeId) {
        ChallengeTitleDescriptionDTO dto = challengeService.getChallengeInfo(challengeId);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/user/{userId}/summary")
    public ResponseEntity<List<UserChallengeSummaryDTO>> getUserChallengeSummary(@PathVariable Integer userId) {
        List<UserChallengeSummaryDTO> summaries = challengeService.getUserChallengeSummaries(userId);
        return ResponseEntity.ok(summaries);
    }

    @GetMapping("/{challengeId}/info")
    public ResponseEntity<ChallengeBasicInfoDTO> getChallengeBasicInfo(@PathVariable Integer challengeId) {
        ChallengeBasicInfoDTO dto = challengeService.getChallengeBasicInfo(challengeId);
        return ResponseEntity.ok(dto);
    }
}