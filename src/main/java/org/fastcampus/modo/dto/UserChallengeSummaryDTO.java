package org.fastcampus.modo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserChallengeSummaryDTO {
    private String category;
    private String title;
    private int participantCount;
}