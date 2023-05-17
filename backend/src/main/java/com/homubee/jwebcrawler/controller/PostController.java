package com.homubee.jwebcrawler.controller;

import com.homubee.jwebcrawler.domain.PostSearch;
import com.homubee.jwebcrawler.dto.request.PostRequestDTO;
import com.homubee.jwebcrawler.dto.response.PostResponseDTO;
import com.homubee.jwebcrawler.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "게시글 단건 조회 API", description = "게시글 정보(단건)를 조회하는 API입니다.\n\n" +
            "postId 해당하는 게시글의 정보를 전달합니다.")
    public PostResponseDTO getSinglePost(@PathVariable("postId") Long postId) {
        return postService.findById(postId);
    }

    @GetMapping("")
    @Operation(summary = "게시글 다수건 조회 API", description = "게시글 정보(다수건)를 조회하는 API입니다.\n\n" +
            "title / content / email / nickname 기준으로 검색이 가능하며, 페이징 기능을 제공합니다.\n\n" +
            "(단, 페이지는 1부터 시작)")
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
    @Operation(summary = "게시글 등록 API", description = "게시글 정보를 등록하는 API입니다.\n\n" +
            "tile / content 정보를 게시판에 등록합니다.\n\n" +
            "(Response Body 없음)")
    public void savePost(@RequestBody PostRequestDTO requestDTO) {
        postService.savePost(requestDTO);
    }

    @PutMapping("/{postId}")
    @Operation(summary = "게시글 단건 수정 API", description = "게시글 정보(단건)를 수정하는 API입니다.\n\n" +
            "postId 해당하는 게시글의 정보를 수정합니다.\n\n" +
            "(Response Body 없음)")
    public void updatePost(@PathVariable("postId") Long postId, @RequestBody PostRequestDTO requestDTO) {
        postService.updatePost(postId, requestDTO);
    }

    @DeleteMapping("/{postId}")
    @Operation(summary = "게시글 단건 삭제 API", description = "게시글 정보(단건)를 삭제하는 API입니다.\n\n" +
            "postId 해당하는 게시글의 정보를 삭제합니다.\n\n" +
            "(Response Body 없음)")
    public void deletePost(@PathVariable("postId") Long postId) {
        postService.deletePost(postId);
    }
}
