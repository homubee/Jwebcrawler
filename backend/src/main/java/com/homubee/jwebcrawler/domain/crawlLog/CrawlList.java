package com.homubee.jwebcrawler.domain.crawlLog;

import lombok.Getter;
import lombok.Setter;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="CRAWL_LIST")
@DiscriminatorValue("LIST")
@Getter
@Setter
public class CrawlList extends CrawlLog {
    private String listTag;
    private String listClass;
    private String listAttr;
    private String targetTag;

    @Override
    public String getResultString(Document document) throws Exception {
        Element root = this.getRootElement(document);

        String resultString = "";
        if (this.listTag == null || this.listTag.isEmpty()) {
            // cannot select
            throw new Exception();
        } else {
            String selectString = this.listTag;
            if (!(this.listAttr == null || this.listAttr.isEmpty())) {
                selectString += "[" + this.listAttr + "]";
            }
            if (!(this.listClass == null || this.listClass.isEmpty())) {
                selectString += "." + this.listClass;
            }
            if (!(this.targetTag == null || this.targetTag.isEmpty())) {
                selectString += " " + this.targetTag;
            }
            // select 'tag[attr].class tag'
            Elements listElements = root.select(selectString);

            for (Element element: listElements) {
                resultString += element.text() + "\n";
            }
        }

        return resultString;
    }
}
