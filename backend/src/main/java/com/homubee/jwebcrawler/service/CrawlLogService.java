package com.homubee.jwebcrawler.service;

import com.homubee.jwebcrawler.domain.crawlLog.CrawlBody;
import com.homubee.jwebcrawler.domain.crawlLog.CrawlList;
import com.homubee.jwebcrawler.domain.crawlLog.CrawlLog;
import com.homubee.jwebcrawler.domain.Member;
import com.homubee.jwebcrawler.domain.crawlLog.CrawlResult;
import com.homubee.jwebcrawler.dto.request.CrawlBodyRequestDTO;
import com.homubee.jwebcrawler.dto.request.CrawlListRequestDTO;
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

    public CrawlResultResponseDTO saveCrawlBody(CrawlBodyRequestDTO requestDTO) {
        Long memberId = requestDTO.getMemberId();

        CrawlBody crawlBody = modelMapper.map(requestDTO, CrawlBody.class);
        CrawlResult crawlResult = this.crawlResult(crawlBody, memberId);
        CrawlResultResponseDTO responseDTO = modelMapper.map(crawlBody, CrawlResultResponseDTO.class);
        responseDTO.setResult(crawlResult);
        return responseDTO;
    }

    public CrawlResultResponseDTO saveCrawlList(CrawlListRequestDTO requestDTO) {
        Long memberId = requestDTO.getMemberId();

        CrawlList crawlList = modelMapper.map(requestDTO, CrawlList.class);
        CrawlResult crawlResult = this.crawlResult(crawlList, memberId);
        CrawlResultResponseDTO responseDTO = modelMapper.map(crawlList, CrawlResultResponseDTO.class);
        responseDTO.setResult(crawlResult);
        return responseDTO;
    }

    private CrawlResult crawlResult(CrawlLog crawlLog, Long memberId) {
        CrawlResult crawlResult = new CrawlResult();
        crawlResult.setTitle("Wrong Member ID");
        crawlResult.setBody("Wrong Member ID");

        // check memberId
        Optional<Member> optionalMember = memberRepository.findById(memberId);
        if (optionalMember.isPresent()) {
            Member member = optionalMember.get();
            crawlLog.setMember(member);

            // try crawling
            Document document;
            try {
                document = Jsoup.connect(crawlLog.getUrl())
                        .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36")
                        .get();
                crawlResult.setTitle(document.title());
                crawlResult.setBody(crawlLog.getResultString(document));
                crawlLogRepository.save(crawlLog);
            } catch (Exception e) {
                crawlResult.setTitle("Crawling Failed");
                crawlResult.setBody("Crawling Failed");
                //e.printStackTrace();
            }
        }
        return crawlResult;
    }

    public List<CrawlLogResponseDTO> findCrawlLogs() {
        List<CrawlLog> crawlLogs = crawlLogRepository.findAll();
        return modelMapper.map(crawlLogs, new TypeToken<List<CrawlLogResponseDTO>>(){}.getType());
    }
}
