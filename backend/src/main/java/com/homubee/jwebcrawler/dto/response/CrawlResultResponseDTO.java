package com.homubee.jwebcrawler.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CrawlResultResponseDTO {
    private Long id;
    private String url;
    private String result;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
