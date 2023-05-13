package com.homubee.jwebcrawler.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CrawlListRequestDTO {
    private Long memberId;
    private String url;
    private String rootId;
    private String rootTag;
    private String rootClass;
    private String rootAttr;
    private String listTag;
    private String listClass;
    private String listAttr;
    private String targetTag;
}
