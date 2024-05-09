package com.diplom.spring_news_agregator.service;

import com.diplom.spring_news_agregator.model.Quote;
import com.diplom.spring_news_agregator.repo.QuoteRepo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuoteServiceImpl implements QuoteService {

    public QuoteServiceImpl(QuoteRepo repo) {
        this.repo = repo;
    }

    QuoteRepo repo;

    @Override
    public void save(Quote quote) {
        repo.save(quote);
    }

    @Override
    public List<Quote> getAllQ() {
        return repo.findAll();
    }

    @Override
    public void parserin() {
        String url = "https://quotes.toscrape.com/";

        try {
            Document doc = Jsoup.connect(url)
                    .userAgent("Chrome/124.0.6367.119")
                    .timeout(5000)
                    .referrer("https://google.com")
                    .get(); // метод

            Elements quoteElements = doc.getElementsByClass("quote");
            for (Element quoteElement : quoteElements) {
                Quote quote = new Quote();
                String text = quoteElement.select(".text").first().text()
                        .replace("“", "")
                        .replace("”", "");

                String author = quoteElement.select(".author").first().text();
                List<String> tags = new ArrayList<>();

                // iterating over the list of tags
                for (Element tag : quoteElement.select(".tag")) {
                    // adding the tag string to the list of tags
                    tags.add(tag.text());
                }
                quote.setText(text);
                quote.setAuthor(author);
                quote.setTags(String.join(", ", tags));
                save(quote);
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
