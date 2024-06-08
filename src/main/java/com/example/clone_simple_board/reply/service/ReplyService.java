package com.example.clone_simple_board.reply.service;

import com.example.clone_simple_board.post.db.PostRepository;
import com.example.clone_simple_board.reply.db.ReplyEntity;
import com.example.clone_simple_board.reply.db.ReplyRepository;
import com.example.clone_simple_board.reply.model.ReplyDto;
import com.example.clone_simple_board.reply.model.ReplyRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final PostRepository postRepository;
    private final ReplyConverter replyConverter;

    public ReplyDto create(
            ReplyRequest replyRequest
    ) {
        var postEntity = postRepository.findById(replyRequest.getPostId()).get();

        System.out.println(postEntity);

        var entity = ReplyEntity.builder()
                .post(postEntity)
                .userName(replyRequest.getUserName())
                .password(replyRequest.getPassword())
                .title(replyRequest.getTitle())
                .content(replyRequest.getContent())
                .status("REGISTERED")
                .repliedAt(LocalDateTime.now())
                .build()
                ;

        replyRepository.save(entity);

        return replyConverter.toDto(entity);
    }

    public List<ReplyDto> findAllByPostId(Long postId) {
        return replyRepository.findAllByPostIdAndStatusOrderByIdDesc(postId, "REGISTERED")
                .stream()
                .map(replyConverter::toDto)
                .toList()
                ;
    }
}
