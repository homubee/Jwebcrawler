package com.homubee.jwebcrawler.domain.crawlLog;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("BODY")
@Getter
@Setter
public class CrawlBody extends CrawlLog {
    private BodyType bodyType;
}
