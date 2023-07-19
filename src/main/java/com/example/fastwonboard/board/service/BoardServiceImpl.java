package com.example.fastwonboard.board.service;

import com.example.fastwonboard.board.domain.Board;
import com.example.fastwonboard.board.domain.PageNum;
import com.example.fastwonboard.board.domain.QBoard;
import com.example.fastwonboard.board.domain.Search;
import com.example.fastwonboard.board.persistence.BoardRepository;
import com.querydsl.core.BooleanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardRepository boardRepo;

    @Override
    public void insertBoard(Board board) {
        boardRepo.save(board);
    }

    @Override
    public void updateBoard(Board board) {
        Board findBoard = boardRepo.findById(board.getSeq()).get();

        findBoard.setTitle(board.getTitle());
        findBoard.setContent(board.getContent());

        boardRepo.save(findBoard);

    }

    @Override
    public void deleteBoard(Board board) {
        boardRepo.deleteById(board.getSeq());
    }

    @Override
    public Board getBoard(Board board) {
        return boardRepo.findById(board.getSeq()).get();
    }

    @Override
    public Page<Board> getBoardList(Search search, PageNum pn) {
        BooleanBuilder builder = new BooleanBuilder();

        QBoard qboard = QBoard.board;

        if(search.getSearchCondition().equals("TITLE")) {
            builder.and(qboard.title.like("%" + search.getSearchKeyword() + "%"));
        } else if(search.getSearchCondition().equals("CONTENT")) {
            builder.and(qboard.content.like("%" + search.getSearchKeyword() + "%"));
        }

        Pageable pageable = PageRequest.of(pn.getNum()-1, 10, Sort.Direction.DESC, "seq");
        return boardRepo.findAll(builder, pageable);
    }

}

