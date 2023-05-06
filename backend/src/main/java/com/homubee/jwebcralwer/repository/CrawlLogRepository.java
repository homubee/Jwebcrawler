package com.homubee.jwebcralwer.repository;

import com.homubee.jwebcralwer.domain.CrawlLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrawlLogRepository extends JpaRepository<CrawlLog, Long> {
}
