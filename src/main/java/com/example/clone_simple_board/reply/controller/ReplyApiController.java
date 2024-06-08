package com.example.clone_simple_board.reply.controller;

import com.example.clone_simple_board.reply.model.ReplyDto;
import com.example.clone_simple_board.reply.model.ReplyRequest;
import com.example.clone_simple_board.reply.service.ReplyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/reply")
@RequiredArgsConstructor
public class ReplyApiController {

    private final ReplyService replyService;

    @PostMapping("")
    public ReplyDto create(
            @Valid
            @RequestBody ReplyRequest replyRequest

    ) {
        return replyService.create(replyRequest);
    }

}
