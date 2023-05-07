package com.homubee.jwebcrawler.service;

import com.homubee.jwebcrawler.domain.CrawlLog;
import com.homubee.jwebcrawler.dto.response.CrawlLogResponseDTO;
import com.homubee.jwebcrawler.repository.CrawlLogRepository;
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
        String crawledResult = "";
        Document document;
        try {
            document = Jsoup.connect(crawlLog.getUrl()).get();
            crawledResult = document.title() + " : " + document.text();
            crawlLogRepository.save(crawlLog);
        } catch (Exception e) {
            crawledResult = "Crawling Failed";
            //e.printStackTrace();
        }
        CrawlLogResponseDTO responseDTO = modelMapper.map(crawlLog, CrawlLogResponseDTO.class);
        responseDTO.setResult(crawledResult);
        return responseDTO;
    }

    public List<CrawlLog> findCrawlLogs() {
        return crawlLogRepository.findAll();
    }

    public CrawlLog findCrawlLogById(Long id) {
        return crawlLogRepository.findById(id).get();
    }
}
