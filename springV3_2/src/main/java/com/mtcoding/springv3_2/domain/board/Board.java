package com.mtcoding.springv3_2.domain.board;

import com.mtcoding.springv3_2.domain.user.User;          // 작성자 연관관계 대상
import jakarta.persistence.*;                              // JPA 매핑 어노테이션
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter                                                     // 전체 Getter (읽기 전용)
@NoArgsConstructor(access = AccessLevel.PROTECTED)         // JPA 기본 생성자 보호
@Table(name = "board_tb")                                  // 매핑될 테이블명
@Entity                                                     // JPA 엔티티
public class Board {

    @Id                                                    // PK
    @GeneratedValue(strategy = GenerationType.IDENTITY)    // AUTO_INCREMENT
    private Integer id;                                    // 게시글 ID

    @Column(nullable = false, length = 100)                // NOT NULL + 길이 제한
    private String title;                                  // 제목

    @Lob                                                   // 긴 본문(Clob)
    private String content;                                // 내용

    @ManyToOne(fetch = FetchType.LAZY)                     // N:1 (게시글 여러개 : 유저 한명)
    @JoinColumn(name = "user_id", nullable = false)        // FK 컬럼명 지정
    private User user;                                     // 작성자

    private LocalDateTime createdAt;                       // 생성 시각

    @PrePersist                                            // 영속화 직전에 자동 세팅
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    // 생성 시 필수값만 받도록 Builder 사용 (Setter 금지)
    @Builder
    public Board(Integer id, String title, String content, User user) {
        this.id = id;                                      // 테스트용으로만 사용 (일반 실행에선 null)
        this.title = title;
        this.content = content;
        this.user = user;
    }

    // 의도 있는 변경만 허용 (Setter 대신)
    public void changeTitleAndContent(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
