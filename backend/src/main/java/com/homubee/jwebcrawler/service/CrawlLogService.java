package com.homubee.jwebcrawler.service;

import com.homubee.jwebcrawler.domain.crawlLog.CrawlBody;
import com.homubee.jwebcrawler.domain.crawlLog.CrawlLog;
import com.homubee.jwebcrawler.domain.Member;
import com.homubee.jwebcrawler.dto.request.CrawlBodyRequestDTO;
import com.homubee.jwebcrawler.dto.response.CrawlLogResponseDTO;
import com.homubee.jwebcrawler.dto.response.CrawlResultResponseDTO;
import com.homubee.jwebcrawler.repository.CrawlLogRepository;
import com.homubee.jwebcrawler.repository.MemberRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CrawlLogService {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    CrawlLogRepository crawlLogRepository;
    @Autowired
    MemberRepository memberRepository;

    public CrawlResultResponseDTO saveCrawlLog(CrawlBodyRequestDTO requestDTO) {
        Long memberId = requestDTO.getMemberId();
        Optional<Member> optionalMember = memberRepository.findById(memberId);

        CrawlLog crawlLog = modelMapper.map(requestDTO, CrawlBody.class);
        String crawledResultTitle = "Wrong Member ID";
        String crawledResultBody = "Wrong Member ID";

        if (optionalMember.isPresent()) {
            Member member = optionalMember.get();
            crawlLog.setMember(member);

            Document document;
            try {
                document = Jsoup.connect(crawlLog.getUrl()).get();
                crawledResultTitle = document.title();
                if (crawlLog.getRootId().equals("")) {
                    if (crawlLog.getRootTag().equals("")) {
                        throw new IllegalStateException();
                    } else {
                        crawledResultBody = document.getElementsByTag(crawlLog.getRootTag().toString()).text();
                    }
                } else {
                    crawledResultBody = document.getElementById(crawlLog.getRootId().toString()).text();
                }
                //crawledResultBody = document.body().text();
                crawlLog = crawlLogRepository.save(crawlLog);
            } catch (Exception e) {
                crawledResultTitle = "Crawling Failed";
                crawledResultBody = "Crawling Failed";
                //e.printStackTrace();
            }
        }
        CrawlResultResponseDTO responseDTO = modelMapper.map(crawlLog, CrawlResultResponseDTO.class);
        responseDTO.setResultTitle(crawledResultTitle);
        responseDTO.setResultBody(crawledResultBody);
        return responseDTO;
    }

    public List<CrawlLogResponseDTO> findCrawlLogs() {
        List<CrawlLog> crawlLogs = crawlLogRepository.findAll();
        return modelMapper.map(crawlLogs, new TypeToken<List<CrawlLogResponseDTO>>(){}.getType());
    }
}
