package com.example.fastwonboard.board.service;

import com.example.fastwonboard.board.domain.Board;
import com.example.fastwonboard.board.domain.Comment;
import com.example.fastwonboard.board.domain.PageNum;
import com.example.fastwonboard.board.domain.QComment;
import com.example.fastwonboard.board.persistence.CommentRepository;
import com.querydsl.core.BooleanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepo;

    @Override
    public void insertComment(Comment comment) {
        System.out.println("댓글 내용" + comment.getCmtContent());
        commentRepo.save(comment);

    }

    @Override
    public void updateComment(Comment comment) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteComment(Comment comment) {
        // TODO Auto-generated method stub

    }

    @Override
    public Page<Comment> getCommentList(PageNum pn, Board board) {
        BooleanBuilder bb = new BooleanBuilder();

        QComment qcmt = QComment.comment;

        bb.and(qcmt.board.seq.eq(board.getSeq()));

        Pageable pageable = PageRequest.of(pn.getCmtPage()-1, 10, Sort.Direction.DESC, "cmtId");
        return commentRepo.findAll(bb, pageable);
    }

}

