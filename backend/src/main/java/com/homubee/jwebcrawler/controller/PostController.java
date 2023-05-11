package com.homubee.jwebcrawler.controller;

import com.homubee.jwebcrawler.dto.request.PostRequestDTO;
import com.homubee.jwebcrawler.dto.response.PostResponseDTO;
import com.homubee.jwebcrawler.service.PostService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
@Tag(name = "[App] Post", description = "게시글 API")
public class PostController {
    @Autowired
    PostService postService;

    @GetMapping("/{postId}")
    public PostResponseDTO getSinglePost(@PathVariable("postId") Long postId) {
        return postService.findById(postId);
    }

    @GetMapping("")
    public List<PostResponseDTO> getMultiplePosts() {
        return postService.findPosts();
    }

    @PostMapping("")
    public void savePost(@RequestBody PostRequestDTO requestDTO) {
        postService.savePost(requestDTO);
    }

    @PutMapping("/{postId}")
    public void updatePost(@PathVariable("postId") Long postId, @RequestBody PostRequestDTO requestDTO) {
        postService.updatePost(postId, requestDTO);
    }

    @DeleteMapping("/{postId}")
    public void updatePost(@PathVariable("postId") Long postId) {
        postService.deletePost(postId);
    }
}
