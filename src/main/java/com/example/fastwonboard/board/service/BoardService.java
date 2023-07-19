package com.example.fastwonboard.board.service;

import com.example.fastwonboard.board.domain.Board;
import com.example.fastwonboard.board.domain.PageNum;
import com.example.fastwonboard.board.domain.Search;
import org.springframework.data.domain.Page;

public interface BoardService {

    void insertBoard(Board board);

    void updateBoard(Board board);

    void deleteBoard(Board board);

    Board getBoard(Board board);

    Page<Board> getBoardList(Search search, PageNum pn);
}
