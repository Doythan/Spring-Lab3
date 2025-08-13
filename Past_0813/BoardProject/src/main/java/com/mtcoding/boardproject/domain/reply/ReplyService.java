// domain/reply/ReplyService.java
package com.mtcoding.boardproject.domain.reply;

import com.mtcoding.boardproject.controller.dto.ReplySaveRequestDTO;
import com.mtcoding.boardproject.domain.board.Board;
import com.mtcoding.boardproject.domain.board.BoardJpaRepository;
import com.mtcoding.boardproject.domain.user.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReplyService {

    private final ReplyJpaRepository replyJpaRepository;
    private final BoardJpaRepository boardJpaRepository;

    // 댓글 쓰기
    @Transactional
    public void 댓글쓰기(ReplySaveRequestDTO reqDTO, User sessionUser) {
        Board board = boardJpaRepository.findById(reqDTO.getId());
        if (board == null) throw new RuntimeException("게시글을 찾을 수 없음 404");

        Reply reply = new Reply(null, reqDTO.getComment(), sessionUser, board);
        replyJpaRepository.save(reply);
    }

    // 댓글 삭제 (작성자 or 게시글 작성자)
    @Transactional
    public void 댓글삭제(int replyId, User sessionUser) {
        Reply reply = replyJpaRepository.findByIdJoinUserAndBoard(replyId);
        if (reply == null) throw new RuntimeException("댓글을 찾을 수 없음 404");

        Integer writerId = reply.getUser().getId();
        Integer boardOwnerId = reply.getBoard().getUser().getId();
        if (!sessionUser.getId().equals(writerId) && !sessionUser.getId().equals(boardOwnerId)) {
            throw new RuntimeException("권한 없음 403");
        }

        replyJpaRepository.deleteById(replyId);
    }
}
