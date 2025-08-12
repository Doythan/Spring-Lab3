package com.mtcoding.boardproject.controller;

import com.mtcoding.boardproject.controller.dto.ReplySaveRequestDTO;
import com.mtcoding.boardproject.domain.reply.ReplyService;
import com.mtcoding.boardproject.domain.user.User;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class ReplyController {

    private final ReplyService replyService;
    private final HttpSession session;

    // 댓글 등록
    @PostMapping("/reply/save")
    public String save(ReplySaveRequestDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) throw new RuntimeException("로그인 하세요");

        replyService.댓글쓰기(reqDTO, sessionUser);
        return "redirect:/board/" + reqDTO.getId();
    }

    // 댓글 삭제
    @PostMapping("/reply/{id}/delete")
    public String delete(@PathVariable("id") int id, @RequestParam("boardId") int boardId) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) throw new RuntimeException("로그인 하세요");

        replyService.댓글삭제(id, sessionUser);
        return "redirect:/board/" + boardId;
    }
}
