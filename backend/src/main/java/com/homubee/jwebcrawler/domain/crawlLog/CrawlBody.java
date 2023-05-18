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

    /**
     * Root 태그에서 주어진 BodyType에 맞는 형태로 html을 구문 분석하여 결과 문자열을 리턴한다.
     * @param document
     * @return String of root tag body text
     * @throws Exception
     */
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
            // get text include whitespace by wholeText method
            resultString = root.wholeText();
        }

        return resultString;
    }
}
