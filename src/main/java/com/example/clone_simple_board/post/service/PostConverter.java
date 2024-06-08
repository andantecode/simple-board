package com.example.clone_simple_board.post.service;

import com.example.clone_simple_board.post.db.PostEntity;
import com.example.clone_simple_board.post.model.PostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PostConverter {

    public PostDto toDto(PostEntity postEntity) {
        return PostDto.builder()
                .id(postEntity.getId())
                .userName(postEntity.getUserName())
                .password(postEntity.getPassword())
                .status(postEntity.getStatus())
                .email(postEntity.getEmail())
                .title(postEntity.getTitle())
                .content(postEntity.getContent())
                .postedAt(postEntity.getPostedAt())
                .boardId(postEntity.getBoard().getId())
                .replyList(postEntity.getReplyList())
                .build();

    }
}
