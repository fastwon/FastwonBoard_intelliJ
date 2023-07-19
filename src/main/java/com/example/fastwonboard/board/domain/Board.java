package com.example.fastwonboard.board.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString(exclude = {"member", "commentList"})
@Entity
public class Board {
    @Id
    @GeneratedValue
    private Long seq;

    private String title;

    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date createDate = new Date();

    @Column(updatable = false)
    private Long cnt = 0L;

    @ManyToOne
    @JoinColumn(name="MEMBER_ID", nullable = false, updatable = false)
    private Member member;

    public void setMember(Member member) {
        this.member = member;
        member.getBoardList().add(this);
    }

    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER)
    private List<Comment> commentList = new ArrayList<Comment>();

}

