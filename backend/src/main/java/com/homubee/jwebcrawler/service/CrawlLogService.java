package com.homubee.jwebcrawler.service;

import com.homubee.jwebcrawler.domain.CrawlLog;
import com.homubee.jwebcrawler.domain.Member;
import com.homubee.jwebcrawler.dto.request.CrawlLogRequestDTO;
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

    public CrawlResultResponseDTO saveCrawlLog(CrawlLogRequestDTO requestDTO) {
        Long memberId = requestDTO.getMemberId();
        Optional<Member> optionalMember = memberRepository.findById(memberId);

        CrawlLog crawlLog = modelMapper.map(requestDTO, CrawlLog.class);
        String crawledResult = "Wrong Member ID";

        if (optionalMember.isPresent()) {
            Member member = optionalMember.get();
            crawlLog.setMember(member);

            Document document;
            try {
                document = Jsoup.connect(crawlLog.getUrl()).get();
                crawledResult = document.title() + " : " + document.text();
                crawlLog = crawlLogRepository.save(crawlLog);
            } catch (Exception e) {
                crawledResult = "Crawling Failed";
                //e.printStackTrace();
            }
        }
        CrawlResultResponseDTO responseDTO = modelMapper.map(crawlLog, CrawlResultResponseDTO.class);
        responseDTO.setResult(crawledResult);
        return responseDTO;
    }

    public List<CrawlLogResponseDTO> findCrawlLogs() {
        List<CrawlLog> crawlLogs = crawlLogRepository.findAll();
        return modelMapper.map(crawlLogs, new TypeToken<List<CrawlLogResponseDTO>>(){}.getType());
    }
}
