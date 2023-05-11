package com.homubee.jwebcrawler.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRequestDTO {
    private Long memberId;
    private Long postId;
    private String content;
}
