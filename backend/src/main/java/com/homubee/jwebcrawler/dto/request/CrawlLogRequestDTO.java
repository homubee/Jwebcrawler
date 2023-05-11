package com.homubee.jwebcrawler.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CrawlLogRequestDTO {
    private Long memberId;
    private String url;
    private String tag;
    private String attr;
}
