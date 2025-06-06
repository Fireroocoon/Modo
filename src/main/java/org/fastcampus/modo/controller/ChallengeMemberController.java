package org.fastcampus.modo.controller;

import org.fastcampus.modo.entity.ChallengeMember;
import org.fastcampus.modo.service.ChallengeMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/challenges")
public class ChallengeMemberController {

    private final ChallengeMemberService challengeMemberService;

    @Autowired
    public ChallengeMemberController(ChallengeMemberService challengeMemberService) {
        this.challengeMemberService = challengeMemberService;
    }

    @PostMapping("/{challengeId}/join")
    public ResponseEntity<ChallengeMember> joinChallenge(@PathVariable Integer challengeId,
                                                         @RequestParam Integer userIdx) {
        ChallengeMember member = challengeMemberService.joinChallenge(challengeId, userIdx);
        return ResponseEntity.ok(member);
    }
}