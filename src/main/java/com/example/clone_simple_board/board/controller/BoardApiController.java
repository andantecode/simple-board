package com.example.clone_simple_board.board.controller;

import com.example.clone_simple_board.board.model.BoardDto;
import com.example.clone_simple_board.board.model.BoardRequest;
import com.example.clone_simple_board.board.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardApiController {

    private final BoardService boardService;

    /**
     * Enter BoardName and Create new Board
     * @param boardRequest boardRequest(string: boardName)
     * @return boardDto
     */
    @PostMapping("")
    public BoardDto create(
            @Valid
            @RequestBody BoardRequest boardRequest
    ) {
        return boardService.create(boardRequest);
    }

    /**
     * View Board by Id
     * @param id Id
     * @return boardDto
     */
    @GetMapping("/id/{id}")
    public BoardDto view(
            @PathVariable Long id
    ) {
        var entity = boardService.view(id);

        log.info("result: {}", entity);
        return entity;
    }

}
