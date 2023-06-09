package com.homubee.jwebcrawler.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CrawlLogResponseDTO {
    private Long id;
    private String url;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
