package org.fastcampus.modo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    @Column(nullable = false)
    private String userid;

    @Column(nullable = false)
    private String userpw;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String email;

    @Column(nullable = true)
    private LocalDate birth;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    private Integer score;

    private String interest;

    @Column(nullable = false)
    private Boolean completed;

    @OneToMany(mappedBy = "user")
    private List<ChallengeMember> challengeMembers;

    @OneToMany(mappedBy = "user")
    private List<AIFeedback> aiFeedbacks;
}