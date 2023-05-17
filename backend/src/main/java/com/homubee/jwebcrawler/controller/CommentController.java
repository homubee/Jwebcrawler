package com.homubee.jwebcrawler.controller;

import com.homubee.jwebcrawler.dto.request.CommentRequestDTO;
import com.homubee.jwebcrawler.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/comments")
@Tag(name = "[App] Comment", description = "댓글 API")
public class CommentController {
    @Autowired
    CommentService commentService;

    @PostMapping("")
    @Operation(summary = "댓글 등록 API", description = "댓글 정보를 등록하는 API입니다.\n\n" +
            "댓글 내용을 게시글에 등록합니다.\n\n" +
            "(Response Body 없음)")
    public void saveComment(@RequestBody CommentRequestDTO requestDTO) {
        commentService.saveComment(requestDTO);
    }
}
