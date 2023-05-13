package com.homubee.jwebcrawler.dto.response;

import com.homubee.jwebcrawler.domain.crawlLog.CrawlResult;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CrawlResultResponseDTO {
    private Long id;
    private String url;
    private CrawlResult result;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
