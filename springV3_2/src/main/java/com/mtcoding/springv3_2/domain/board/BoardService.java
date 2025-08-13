package com.mtcoding.springv3_2.domain.board;

import com.mtcoding.springv3_2.controller.dto.BoardDetailResponseDTO; // 상세 DTO
import com.mtcoding.springv3_2.controller.dto.BoardResponseDTO;      // 목록 DTO
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor // final 필드의 생성자를 롬복이 만들어줌 (의존성 주입)
@Service                  // 서비스 빈 등록
public class BoardService {

    private final BoardJpaRepository boardRepository; // 레포지토리 의존성

    /**
     * 게시글 목록 조회
     * - 엔티티를 직접 반환하지 않고 DTO로 변환해서 리턴
     * - 뷰에 필요한 데이터만 안전하게 전달
     */
    public List<BoardResponseDTO> findAll() {
        return boardRepository.findAll().stream()
                .map(BoardResponseDTO::of) // 엔티티 -> DTO
                .collect(Collectors.toList()); // JDK 호환성 위해 Collectors 사용
    }

    /**
     * 게시글 상세 조회
     * - 작성자(User)까지 한 번에 조회하기 위해 fetch join 사용하는 쿼리 호출
     * - DTO로 감싸 리턴
     */
    public BoardDetailResponseDTO findDetail(int id) {
        return new BoardDetailResponseDTO(boardRepository.findByIdJoinUser(id));
    }
}
