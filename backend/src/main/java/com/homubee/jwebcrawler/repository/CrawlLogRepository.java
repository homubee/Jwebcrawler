package com.homubee.jwebcrawler.repository;

import com.homubee.jwebcrawler.domain.CrawlLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrawlLogRepository extends JpaRepository<CrawlLog, Long> {
}
