package com.mtcoding.springv3_2.domain.user;

import jakarta.persistence.*; // JPA 매핑 어노테이션
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter // 모든 필드의 Getter 생성
@NoArgsConstructor(access = AccessLevel.PROTECTED) // JPA 기본 생성자 (protected로 막아두기)
@Table(name = "user_tb") // 매핑할 테이블명 지정
@Entity // JPA 엔티티 선언
public class User {

    @Id // PK 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT 설정
    private Integer id; // PK 컬럼

    @Column(unique = true, nullable = false, length = 30) // 유니크 + NOT NULL + 길이 제한
    private String username; // 사용자 이름

    // 생성자 대신 @Builder 사용해서 필요한 필드만 초기화
    @Builder
    public User(Integer id, String username) {
        this.id = id;
        this.username = username;
    }

    // Setter 대신 필요한 값만 변경하는 메서드, 변경 가능성을 최소화 의도를 가진 변경만 허용
    public void changeUsername(String newUsername) {
        this.username = newUsername;
    }
}