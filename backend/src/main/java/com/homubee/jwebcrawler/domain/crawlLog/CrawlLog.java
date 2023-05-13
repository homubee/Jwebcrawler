package com.homubee.jwebcrawler.domain.crawlLog;

import com.homubee.jwebcrawler.domain.BaseEntity;
import com.homubee.jwebcrawler.domain.Member;
import lombok.Getter;
import lombok.Setter;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

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

    public abstract String getResultString(Document document) throws Exception;

    public Element getRootElement(Document document) throws Exception {
        Element root;

        if (this.rootId == null || this.rootId.isEmpty()) {
            if (this.rootTag == null || this.rootTag.isEmpty()) {
                // cannot select
                throw new Exception();
            } else {
                String selectString = this.rootTag;
                if (!(this.rootAttr == null || this.rootAttr.isEmpty())) {
                    selectString += "[" + this.rootAttr + "]";
                }
                if (!(this.rootClass == null || this.rootClass.isEmpty())) {
                    selectString += "." + this.rootClass;
                }
                // select 'tag[attr].class'
                root = document.selectFirst(selectString);
            }
        } else {
            // select element by id
            root = document.getElementById(this.rootId);
        }
        return root;
    }
}
