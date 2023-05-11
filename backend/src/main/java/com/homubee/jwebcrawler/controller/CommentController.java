package com.homubee.jwebcrawler.controller;

import com.homubee.jwebcrawler.dto.request.CommentRequestDTO;
import com.homubee.jwebcrawler.service.CommentService;
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
    public void saveComment(@RequestBody CommentRequestDTO requestDTO) {
        commentService.saveComment(requestDTO);
    }
}
