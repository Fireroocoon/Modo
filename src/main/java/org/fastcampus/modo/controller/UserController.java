package org.fastcampus.modo.controller;

import org.fastcampus.modo.dto.UserChallengeStatusDTO;
import org.fastcampus.modo.entity.User;
import org.fastcampus.modo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 관심사 추가
    @PostMapping("/{userId}/interests")
    public ResponseEntity<User> addInterest(@PathVariable Long userId,
                                            @RequestParam String interest) {
        User updatedUser = userService.addInterest(userId, interest);
        return ResponseEntity.ok(updatedUser);
    }

    // 관심사 삭제
    @DeleteMapping("/{userId}/interests")
    public ResponseEntity<User> removeInterest(@PathVariable Long userId,
                                               @RequestParam String interest) {
        User updatedUser = userService.removeInterest(userId, interest);
        return ResponseEntity.ok(updatedUser);
    }

    // 닉네임 변경
    @PutMapping("/{userId}/nickname")
    public ResponseEntity<User> updateNickname(@PathVariable Long userId,
                                               @RequestParam String nickname) {
        User updatedUser = userService.updateNickname(userId, nickname);
        return ResponseEntity.ok(updatedUser);
    }

    // 생년월일 변경
    @PutMapping("/{userId}/birth")
    public ResponseEntity<User> updateBirth(@PathVariable Long userId,
                                            @RequestParam String birth) {
        LocalDate parsedBirth = LocalDate.parse(birth); // 예: "2008-03-21"
        User updatedUser = userService.updateBirth(userId, parsedBirth);
        return ResponseEntity.ok(updatedUser);
    }

    @GetMapping("/{userId}/challenge-status")
    public ResponseEntity<UserChallengeStatusDTO> getUserChallengeStatus(@PathVariable Integer userId) {
        UserChallengeStatusDTO dto = userService.getUserChallengeStatus(userId);
        return ResponseEntity.ok(dto);
    }
}