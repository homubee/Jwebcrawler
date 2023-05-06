package com.homubee.jwebcralwer.controller;

import com.homubee.jwebcralwer.domain.CrawlLog;
import com.homubee.jwebcralwer.dto.request.CrawlLogRequestDTO;
import com.homubee.jwebcralwer.dto.response.CrawlLogResponseDTO;
import com.homubee.jwebcralwer.service.CrawlLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/crawl")
@Tag(name = "[App] Crawl", description = "크롤링 API")
public class CrawlController {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    CrawlLogService crawlLogService;

    @PostMapping("")
    @Operation(summary = "크롤링 요청 API", description = "크롤링을 요청하는 API입니다.")
    public CrawlLogResponseDTO crawl(@RequestBody CrawlLogRequestDTO requestDTO) {
        CrawlLog crawlLog = modelMapper.map(requestDTO, CrawlLog.class);
        CrawlLogResponseDTO responseDTO = crawlLogService.saveCrawlLog(crawlLog);
        return responseDTO;
    }
}
