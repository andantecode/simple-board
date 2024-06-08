package com.example.clone_simple_board.reply.service;

import com.example.clone_simple_board.reply.db.ReplyEntity;
import com.example.clone_simple_board.reply.model.ReplyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReplyConverter {

    public ReplyDto toDto(ReplyEntity replyEntity) {
        return ReplyDto.builder()
                .id(replyEntity.getId())
                .postId(replyEntity.getPost().getId())
                .userName(replyEntity.getUserName())
                .password(replyEntity.getPassword())
                .title(replyEntity.getTitle())
                .content(replyEntity.getContent())
                .status(replyEntity.getStatus())
                .repliedAt(replyEntity.getRepliedAt())
                .build();
    }
}
