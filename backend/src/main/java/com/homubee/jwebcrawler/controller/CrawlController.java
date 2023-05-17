package com.homubee.jwebcrawler.controller;

import com.homubee.jwebcrawler.dto.request.CrawlBodyRequestDTO;
import com.homubee.jwebcrawler.dto.request.CrawlListRequestDTO;
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
@Tag(name = "[App & Admin] Crawl", description = "크롤링 API")
public class CrawlController {

    @Autowired
    CrawlLogService crawlLogService;

    @GetMapping("")
    @Operation(summary = "크롤링 이력 조회 API", description = "크롤링 이력을 조회하는 API입니다.\n\n" +
            "전체 크롤링 이력 목록을 전달합니다.")
    public List<CrawlLogResponseDTO> getMultipleCrawlLogs() {
        return crawlLogService.findCrawlLogs();
    }

    @PostMapping("/body")
    @Operation(summary = "본문 크롤링 API", description = "본문 내용을 크롤링하는 API입니다.\n\n" +
            "주어진 url의 root 속성에 해당하고, bodyType에 따라 본문 내용을 구문 분석 후 크롤링합니다.\n\n" +
            "memberId, url, bodyType은 필수값\n\n" +
            "root 속성의 경우, rootId / rootTag,rootClass,rootAttr 둘 중 선택")
    public CrawlResultResponseDTO crawlBody(@RequestBody CrawlBodyRequestDTO requestDTO) {
        CrawlResultResponseDTO responseDTO = crawlLogService.saveCrawlBody(requestDTO);
        return responseDTO;
    }

    @PostMapping("/list")
    @Operation(summary = "목록 크롤링 API", description = "목록 항목을 크롤링하는 API입니다.\n\n" +
            "주어진 url의 root 속성에 해당하고, list 속성에 해당하며, targetTag 이름을 가진 목록 요소를 구문 분석 후 크롤링합니다.\n\n" +
            "memberId, url은 필수값\n\n" +
            "root 속성의 경우, rootId / rootTag,rootClass,rootAttr 둘 중 선택\n\n" +
            "list 속성의 경우, listId / listTag,listClass,listAttr 둘 중 선택")
    public CrawlResultResponseDTO crawlList(@RequestBody CrawlListRequestDTO requestDTO) {
        CrawlResultResponseDTO responseDTO = crawlLogService.saveCrawlList(requestDTO);
        return responseDTO;
    }
}
