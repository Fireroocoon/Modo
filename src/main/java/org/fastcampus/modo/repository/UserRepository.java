package org.fastcampus.modo.repository;

import org.fastcampus.modo.entity.Challenge;
import org.fastcampus.modo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // 기본 CRUD 메서드 사용
}