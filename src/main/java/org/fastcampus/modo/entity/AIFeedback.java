package org.fastcampus.modo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "ai_feedback")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AIFeedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer feedbackId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(length = 50, nullable = true)
    private String topic;

    @Column(columnDefinition = "TEXT", nullable = true)
    private String content;

    @Column(columnDefinition = "TEXT", nullable = true)
    private String content2;

    @Column(columnDefinition = "TEXT", nullable = true)
    private String method;

    @Column(nullable = true)
    private LocalDateTime createdAt;
}
