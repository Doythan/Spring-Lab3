package com.mtcoding.springv3_2.controller.dto;

import com.mtcoding.springv3_2.domain.board.Board; // 엔티티 -> DTO 변환
import lombok.Getter;

@Getter // 읽기 전용
public class BoardDetailResponseDTO {
    private final Integer id;       // 게시글 ID
    private final String title;     // 제목
    private final String content;   // 본문
    private final String username;  // 작성자 이름

    // 엔티티를 받아서 필요한 필드만 매핑 (생성 시 불변)
    public BoardDetailResponseDTO(Board b) {
        this.id = b.getId();
        this.title = b.getTitle();
        this.content = b.getContent();
        this.username = b.getUser().getUsername();
    }
}
