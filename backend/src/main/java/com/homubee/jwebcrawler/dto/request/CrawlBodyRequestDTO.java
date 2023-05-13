package com.homubee.jwebcrawler.dto.request;

import com.homubee.jwebcrawler.domain.crawlLog.BodyType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CrawlBodyRequestDTO {
    private Long memberId;
    private String url;
    private String rootId;
    private String rootTag;
    private String rootClass;
    private String rootAttr;
    private BodyType bodyType;
}
