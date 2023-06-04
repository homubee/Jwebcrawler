package com.homubee.jwebcrawler.repository;

import com.homubee.jwebcrawler.domain.crawlLog.CrawlLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CrawlLogRepository extends JpaRepository<CrawlLog, Long> {
    List<CrawlLog> findAllByOrderByCreatedAtDesc();
}
