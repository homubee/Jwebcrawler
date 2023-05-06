package com.homubee.jwebcralwer.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="CRAWL_LOG")
@SequenceGenerator(
        name = "CRAWL_LOG_SEQ_GENERATOR",
        sequenceName = "CRAWL_LOG_SEQ",
        initialValue = 1, allocationSize = 1)
@Getter
@Setter
public class CrawlLog extends BaseEntity {
    @Id @GeneratedValue(generator = "CRAWL_LOG_SEQ_GENERATOR")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="MEMBER_ID")
    private Member member;

    private String url;

    private String tag;

    private String attr;
}
