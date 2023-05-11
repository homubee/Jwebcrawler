package com.homubee.jwebcrawler.controller;

import com.homubee.jwebcrawler.dto.request.CrawlLogRequestDTO;
import com.homubee.jwebcrawler.dto.response.CrawlLogResponseDTO;
import com.homubee.jwebcrawler.dto.response.CrawlResultResponseDTO;
import com.homubee.jwebcrawler.service.CrawlLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/crawl")
@Tag(name = "[App] Crawl", description = "크롤링 API")
public class CrawlController {

    @Autowired
    CrawlLogService crawlLogService;

    @GetMapping("")
    public List<CrawlLogResponseDTO> getMultipleCrawlLogs() {
        return crawlLogService.findCrawlLogs();
    }

    @PostMapping("")
    @Operation(summary = "크롤링 요청 API", description = "크롤링을 요청하는 API입니다.")
    public CrawlResultResponseDTO crawl(@RequestBody CrawlLogRequestDTO requestDTO) {
        CrawlResultResponseDTO responseDTO = crawlLogService.saveCrawlLog(requestDTO);
        return responseDTO;
    }
}
