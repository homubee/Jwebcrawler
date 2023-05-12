package com.homubee.jwebcrawler.domain.crawlLog;

import com.homubee.jwebcrawler.domain.BaseEntity;
import com.homubee.jwebcrawler.domain.Member;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="CRAWL_LOG")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "DTYPE")
@SequenceGenerator(
        name = "CRAWL_LOG_SEQ_GENERATOR",
        sequenceName = "CRAWL_LOG_SEQ",
        initialValue = 1, allocationSize = 1)
@Getter
@Setter
public abstract class CrawlLog extends BaseEntity {
    @Id @GeneratedValue(generator = "CRAWL_LOG_SEQ_GENERATOR")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="MEMBER_ID")
    private Member member;

    private String url;

    private String rootId;
    private String rootTag;
    private String rootClass;
    private String rootAttr;
}
