package com.homubee.jwebcrawler.dto.request;

import com.homubee.jwebcrawler.domain.Member;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostRequestDTO {
    private Member member;
    private String title;
    private String content;
}
