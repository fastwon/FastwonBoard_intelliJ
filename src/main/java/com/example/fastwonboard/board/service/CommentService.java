package com.example.fastwonboard.board.service;

import com.example.fastwonboard.board.domain.Board;
import com.example.fastwonboard.board.domain.Comment;
import com.example.fastwonboard.board.domain.PageNum;
import org.springframework.data.domain.Page;

public interface CommentService {

    void insertComment(Comment comment);

    void updateComment(Comment comment);

    void deleteComment(Comment comment);

    Page<Comment> getCommentList(PageNum pn, Board board);
}

