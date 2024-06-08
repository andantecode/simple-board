package com.example.clone_simple_board.post.service;

import com.example.clone_simple_board.board.db.BoardRepository;
import com.example.clone_simple_board.common.Api;
import com.example.clone_simple_board.common.Pagination;
import com.example.clone_simple_board.post.db.PostEntity;
import com.example.clone_simple_board.post.db.PostRepository;
import com.example.clone_simple_board.post.model.PostDto;
import com.example.clone_simple_board.post.model.PostRequest;
import com.example.clone_simple_board.post.model.PostViewRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final BoardRepository boardRepository;
    private final PostRepository postRepository;
    private final PostConverter postConverter;

    public PostDto create(PostRequest postRequest) {
        var boardEntity = boardRepository.findById(postRequest.getBoardId()).get();

        var entity = PostEntity.builder()
                .board(boardEntity)
                .userName(postRequest.getUserName())
                .password(postRequest.getPassword())
                .email(postRequest.getEmail())
                .status("REGISTERED")
                .title(postRequest.getTitle())
                .content(postRequest.getContent())
                .postedAt(LocalDateTime.now())
                .build()
                ;

        postRepository.save(entity);

        return postConverter.toDto(entity);
    }

    /**
     * #1 isExistsPost & #2 isMatchesPassword
     * @param postViewRequest
     * @return
     */
    public PostDto view(PostViewRequest postViewRequest) {

        // Valid #1
        var entity = postRepository.findFirstByIdAndStatusOrderByIdDesc(postViewRequest.getPostId(), "REGISTERED")
                .map(it -> {
                    // Valid #2
                    if(!it.getPassword().equals(postViewRequest.getPassword())) {
                        var format = "Password is Not Matched %s vs %s";

                        throw new RuntimeException(String.format(format, it.getPassword(), postViewRequest.getPassword()));
                    }
                    return it;
                }).orElseThrow(
                        () -> new RuntimeException("Post not found: [" + postViewRequest.getPostId() + "]")
                );

        return postConverter.toDto(entity);
    }

    public Api<List<PostDto>> all(Pageable pageable) {
        var list = postRepository.findAll(pageable);

        var pagination = Pagination.builder()
                .page(list.getNumber())
                .size(list.getSize())
                .currentElements(list.getNumberOfElements())
                .totalElements(list.getTotalElements())
                .totalPage(list.getTotalPages())
                .build();

        var dtoList = list.stream()
                .map(postConverter::toDto)
                .toList();

        var response = Api.<List<PostDto>>builder()
                .body(dtoList)
                .pagination(pagination)
                .build();

        return response;
    }

    /**
     * #1 isExistsPost & #2 isMatchesPassword
     * @param postViewRequest
     */
    public void delete(PostViewRequest postViewRequest) {
        postRepository.findById(postViewRequest.getPostId())
                .map(it-> {
                  if(!it.getPassword().equals(postViewRequest.getPassword())) {
                      var format = "Password is Not Matched %s vs %s";
                      throw new RuntimeException(String.format(format, it.getPassword(), postViewRequest.getPassword()));
                  }
                  it.setStatus("UNREGISTERED");
                  postRepository.save(it);
                  return it;
                }).orElseThrow(
                        () -> new RuntimeException("Post not found: [" + postViewRequest.getPostId() + "]")
                );
    }
}
