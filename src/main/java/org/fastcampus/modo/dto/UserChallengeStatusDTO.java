package org.fastcampus.modo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UserChallengeStatusDTO {
    private List<String> ongoingChallenges;   // 참여 중인 챌린지 제목 목록
    private List<String> completedChallenges; // 완료한 챌린지 제목 목록
    private Integer totalScore;               // 유저의 점수
}