package com.example.simple_board.board.controller;

import com.example.simple_board.board.db.BoardEntity;
import com.example.simple_board.board.model.BoardRequest;
import com.example.simple_board.board.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 게시판 Controller
 * 게시판 생성
 *
 * @author andantecode
 */
@Slf4j
@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardApiController {

    private final BoardService boardService;

    /**
     * 게시판 생성
     * @param boardRequest
     * @return
     */
    @PostMapping("")
    public BoardEntity create(
        @Valid
        @RequestBody
        BoardRequest boardRequest
    ) {
        return boardService.create(boardRequest);
    }

    @GetMapping("/id/{id}")
    public BoardEntity view(
            @PathVariable Long id
    ) {
        var entity = boardService.view(id);
        log.info("result: {}", entity);
        return entity;
    }
}
