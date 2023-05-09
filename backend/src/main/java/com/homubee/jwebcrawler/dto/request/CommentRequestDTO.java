package com.homubee.jwebcrawler.dto.request;

import com.homubee.jwebcrawler.domain.Member;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRequestDTO {
    private Member member;
    private PostRequestDTO post;
    private String content;
}
