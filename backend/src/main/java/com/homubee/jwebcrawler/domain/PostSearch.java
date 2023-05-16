package com.homubee.jwebcrawler.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PostSearch {
    private String title;
    private String content;
    private String memberEmail;
    private String memberNickname;
}
