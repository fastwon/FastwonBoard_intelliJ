package com.example.fastwonboard.board.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString(exclude = "board")
@Entity
public class Comment {
    @Id
    @GeneratedValue
    private Long cmtId;

    private String cmtWriter;

    private String cmtContent;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date cmtCreateDate = new Date();

    @ManyToOne
    @JoinColumn(name="seq", nullable = false, updatable = false)
    private Board board;

    public void setBoard(Board board) {
        this.board = board;
        board.getCommentList().add(this);
    }
}

