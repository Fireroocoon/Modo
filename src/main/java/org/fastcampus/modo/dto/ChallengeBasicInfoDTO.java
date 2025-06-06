package org.fastcampus.modo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChallengeBasicInfoDTO {
    private String category;
    private String title;
    private String color;
}