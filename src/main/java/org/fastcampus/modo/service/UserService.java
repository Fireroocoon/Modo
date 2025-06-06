package org.fastcampus.modo.service;

import org.fastcampus.modo.dto.UserChallengeStatusDTO;
import org.fastcampus.modo.entity.Challenge;
import org.fastcampus.modo.entity.ChallengeMember;
import org.fastcampus.modo.entity.User;
import org.fastcampus.modo.repository.ChallengeMemberRepository;
import org.fastcampus.modo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ChallengeMemberRepository challengeMemberRepository;

    @Autowired
    public UserService(UserRepository userRepository, ChallengeMemberRepository challengeMemberRepository) {
        this.userRepository = userRepository;
        this.challengeMemberRepository = challengeMemberRepository;
    }

    // 관심사 추가
    public User addInterest(Long userId, String newInterest) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Set<String> interests = parseInterests(user.getInterest());
        interests.add(newInterest);

        user.setInterest(String.join(",", interests));
        return userRepository.save(user);
    }

    // 관심사 삭제
    public User removeInterest(Long userId, String interestToRemove) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Set<String> interests = parseInterests(user.getInterest());
        interests.remove(interestToRemove);

        user.setInterest(String.join(",", interests));
        return userRepository.save(user);
    }

    // 관심사 문자열을 Set으로 변환
    private Set<String> parseInterests(String interestString) {
        if (interestString == null || interestString.isBlank()) return new HashSet<>();
        return Arrays.stream(interestString.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toSet());
    }

    public User updateNickname(Long userId, String newNickname) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자를 찾을 수 없습니다."));
        user.setNickname(newNickname);
        return userRepository.save(user);
    }

    public User updateBirth(Long userId, LocalDate newBirth) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자를 찾을 수 없습니다."));
        user.setBirth(newBirth);
        return userRepository.save(user);
    }

    public UserChallengeStatusDTO getUserChallengeStatus(Integer userId) {
        User user = userRepository.findById(Long.valueOf(userId))
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<ChallengeMember> members = challengeMemberRepository.findByUser(user);

        List<String> ongoing = new ArrayList<>();
        List<String> completed = new ArrayList<>();

        for (ChallengeMember member : members) {
            Challenge challenge = member.getChallenge();
            if (Boolean.TRUE.equals(challenge.getIsCompleted())) {
                completed.add(challenge.getTitle());
            } else {
                ongoing.add(challenge.getTitle());
            }
        }

        return new UserChallengeStatusDTO(
                ongoing,
                completed,
                user.getScore() != null ? user.getScore() : 0
        );
    }
}