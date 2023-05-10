package com.homubee.jwebcrawler.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostRequestDTO {
    private Long memberId;
    private String title;
    private String content;
}
