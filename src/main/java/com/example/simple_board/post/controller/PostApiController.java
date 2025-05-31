package com.example.simple_board.post.controller;

import com.example.simple_board.post.db.PostEntity;
import com.example.simple_board.post.model.PostRequest;
import com.example.simple_board.post.model.PostViewRequest;
import com.example.simple_board.post.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 게시글 Controller
 * 게시글 생성
 *
 * @author andantecode
 */
@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostApiController {

    private final PostService postService;

    /**
     * 게시글 생성
     * @param postRequest
     * @return
     */
    @PostMapping("")
    public PostEntity create(
            @Valid
            @RequestBody
            PostRequest postRequest
    ) {
        return postService.create(postRequest);
    }

    @PostMapping("/view")
    public PostEntity view(
            @Valid
            @RequestBody PostViewRequest postViewRequest
    ) {
        return postService.view(postViewRequest);
    }

    @GetMapping("/all")
    public List<PostEntity> list() {
        return postService.all();
    }

    @PostMapping("/delete")
    public void delete(
            @Valid
            @RequestBody PostViewRequest postViewRequest
    ) {
        postService.delete(postViewRequest);
    }
}
