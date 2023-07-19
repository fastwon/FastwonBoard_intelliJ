package com.example.fastwonboard.board.persistence;

import com.example.fastwonboard.board.domain.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Long>, QuerydslPredicateExecutor<Comment> {

    @Query("SELECT c FROM Comment c")
    Page<Comment> getCommentList(Pageable pageable);



}
