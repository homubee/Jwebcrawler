package com.homubee.jwebcrawler.controller;

import com.homubee.jwebcrawler.domain.PostSearch;
import com.homubee.jwebcrawler.dto.request.PostRequestDTO;
import com.homubee.jwebcrawler.dto.response.PostResponseDTO;
import com.homubee.jwebcrawler.service.PostService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

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
    public Page<PostResponseDTO> getMultiplePosts(@RequestParam(value = "title", required = false) String title,
                                                  @RequestParam(value = "content", required = false) String content,
                                                  @RequestParam(value = "email", required = false) String email,
                                                  @RequestParam(value = "nickname", required = false) String nickname,
                                                  @PageableDefault Pageable pageable) {
        return postService.findPosts(
                PostSearch.builder()
                        .title(title)
                        .content(content)
                        .memberEmail(email)
                        .memberNickname(nickname)
                        .build(), pageable
        );
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
