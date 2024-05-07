package com.diplom.spring_news_agregator.newsjobs;

import com.diplom.spring_news_agregator.model.News;
import com.diplom.spring_news_agregator.service.NewsService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ParseTask {

    public ParseTask(NewsService newsService) {
        this.newsService = newsService;
    }

   // @Autowired
    NewsService newsService;

    @Scheduled(fixedDelay = 10000)
    public void parseNewNews() {
        String url = "https://news.ycombinator.com/";

        try {
            Document document = Jsoup.connect(url)
                    .userAgent("Google Chrome")
                    .timeout(5000)
                    .referrer("https://google.com")
                    .get();
            Elements newsElements = document.getElementsByClass("storylink");
            for (Element el : newsElements) {
                String title = el.ownText();
                if(!newsService.isExist(title)){
                    News obj = new News();
                    obj.setTitle(title);
                    newsService.save(obj);

                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}
