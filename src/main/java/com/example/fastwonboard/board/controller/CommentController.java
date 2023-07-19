package com.example.fastwonboard.board.controller;

import com.example.fastwonboard.board.domain.Comment;
import com.example.fastwonboard.board.security.SecurityUser;
import com.example.fastwonboard.board.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/comment/insertComment")
    public String insertComment(Comment comment, @AuthenticationPrincipal SecurityUser principal) {
        comment.setCmtWriter(principal.getMember().getName());
        System.out.println("--------------" + principal.getMember().getName());
        commentService.insertComment(comment);

        return "redirect:/board/getBoard?seq=" + comment.getBoard().getSeq();
    }

}

