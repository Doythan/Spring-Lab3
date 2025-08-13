package com.mtcoding.springv3_2.controller.dto;

import com.mtcoding.springv3_2.domain.board.Board; // 엔티티 -> DTO 변환에 사용
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter // 읽기 전용
@AllArgsConstructor // 모든 필드 받는 생성자
public class BoardResponseDTO {
    private Integer id;       // 게시글 ID
    private String title;     // 제목
    private String username;  // 작성자 이름

    // 엔티티를 DTO로 변환하는 팩토리 메서드 (뷰에 필요한 필드만 추출)
    public static BoardResponseDTO of(Board b) {
        return new BoardResponseDTO(
                b.getId(),
                b.getTitle(),
                b.getUser().getUsername()
        );
    }
}
