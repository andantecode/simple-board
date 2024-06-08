package com.example.clone_simple_board.post.controller;

import com.example.clone_simple_board.common.Api;
import com.example.clone_simple_board.post.model.PostDto;
import com.example.clone_simple_board.post.model.PostRequest;
import com.example.clone_simple_board.post.model.PostViewRequest;
import com.example.clone_simple_board.post.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostApiController {

    private final PostService postService;

    /**
     * Create Post by postRequest
     * @param postRequest postRequest (boardId, name, password, email, title, content)
     * @return postDto
     */
    @PostMapping("")
    public PostDto create(
            @Valid
            @RequestBody PostRequest postRequest
    ) {
        return postService.create(postRequest);
    }

    /**
     * Read Post by postId and password
     * @param postViewRequest postViewRequest (postId, password)
     * @return postDto
     */
    @PostMapping("/view")
    public PostDto view(
            @Valid
            @RequestBody PostViewRequest postViewRequest
    ) {
        return postService.view(postViewRequest);
    }

    /**
     * Read All Post by Paging with query param
     * @param pageable query param(page = {})
     * @return Api( body: posts targeted by pageNumber & pagination: Paging information )
     */
    @GetMapping("/all")
    public Api<List<PostDto>> list(
            @PageableDefault(page = 0, size = 10, sort="id", direction = Sort.Direction.DESC)
            Pageable pageable
    ) {
        return postService.all(pageable);
    }

    @PostMapping("/delete")
    public void delete(
            @Valid
            @RequestBody PostViewRequest postViewRequest
    ) {
        postService.delete(postViewRequest);
    }

}
