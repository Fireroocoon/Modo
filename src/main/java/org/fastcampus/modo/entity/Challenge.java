package org.fastcampus.modo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "challenges")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Challenge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer challengeId;

    @Column(nullable = false, length = 50)
    private String title;

    @Lob
    private String description;

    private Boolean isTeam;

    private Boolean isShared;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "creator_id", nullable = false)
    private User creator;

    @OneToMany(mappedBy = "challenge")
    private List<ChallengeMember> challengeMembers;

    @Column(nullable = false)
    private Boolean isCompleted = false; // 기본값 false

    @Column(nullable = false)
    private String color;

    @Column(nullable = false)
    private String category;
}