package com.homubee.jwebcrawler.domain.crawlLog;

import lombok.Getter;
import lombok.Setter;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.persistence.*;

@Entity
@Table(name="CRAWL_BODY")
@DiscriminatorValue("BODY")
@Getter
@Setter
public class CrawlBody extends CrawlLog {
    @Enumerated(EnumType.STRING)
    private BodyType bodyType;

    @Override
    public String getResultString(Document document) throws Exception {
        Element root = this.getRootElement(document);

        String resultString = "";
        if (BodyType.PAGE.equals(this.bodyType)) {
            Elements elements = root.getElementsByTag("p");
            for (Element element : elements) {
                resultString += element.text() + "\n";
            }
        } else if (BodyType.BREAK.equals(this.bodyType)) {
            resultString = root.wholeText();
        }

        return resultString;
    }
}
