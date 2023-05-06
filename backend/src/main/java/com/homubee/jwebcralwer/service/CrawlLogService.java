package com.homubee.jwebcralwer.service;

import com.homubee.jwebcralwer.domain.CrawlLog;
import com.homubee.jwebcralwer.dto.response.CrawlLogResponseDTO;
import com.homubee.jwebcralwer.repository.CrawlLogRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CrawlLogService {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    CrawlLogRepository crawlLogRepository;

    public CrawlLogResponseDTO saveCrawlLog(CrawlLog crawlLog) {
        CrawlLogResponseDTO responseDTO = modelMapper.map(crawlLog, CrawlLogResponseDTO.class);
        Document document;
        try {
            document = Jsoup.connect(crawlLog.getUrl()).get();
            responseDTO.setResult(document.title() + " : " + document.text());
            crawlLogRepository.save(crawlLog);
        } catch (Exception e) {
            responseDTO.setResult("Crawling Failed");
            //e.printStackTrace();
        }
        return responseDTO;
    }

    public List<CrawlLog> findCrawlLogs() {
        return crawlLogRepository.findAll();
    }

    public CrawlLog findCrawlLogById(Long id) {
        return crawlLogRepository.findById(id).get();
    }
}
