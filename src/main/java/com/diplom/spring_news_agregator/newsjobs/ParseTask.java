package com.diplom.spring_news_agregator.newsjobs;

import com.diplom.spring_news_agregator.model.News;
import com.diplom.spring_news_agregator.service.NewsService;
import com.diplom.spring_news_agregator.service.QuoteService;
import com.diplom.spring_news_agregator.service.QuoteServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    private static final Logger LOGGER = LogManager.getLogger(ParseTask.class);

    public ParseTask(NewsService newsService,  QuoteService imp) {
        this.newsService = newsService;
        this.imp = imp;
    }

    NewsService newsService;
    QuoteService imp;

    @Scheduled(fixedDelay = 1000000)
    public void parseQuote() {
        imp.parserin();
    }

    @Scheduled(fixedDelay = 10000) // кожні 10 сек метод робить запит
    public void parseNewNews() {
        String url = "https://quotes.toscrape.com/"; // https://news.ycombinator.com/

        try {
            Document document = Jsoup.connect(url)
                    .userAgent("Chrome/124.0.6367.119")
                    .timeout(5000)
                    .referrer("https://google.com")
                    .get(); // метод
             Elements newsElements = document.getElementsByClass("author");
            LOGGER.info("Found " + newsElements.size() + " new news"); // show 30
            for (Element el : newsElements) { // для кожного єлемента

                  String title = el.ownText();
                if (!newsService.isExist(title)) { // якщо новини не було
                    News obj = new News(); // робимо нову
                    LOGGER.info("Adding new news " + title); // ""
                    obj.setTitle(title);
                    newsService.save(obj);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
