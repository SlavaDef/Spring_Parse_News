package com.diplom.spring_news_agregator.exampl;

import com.diplom.spring_news_agregator.model.Quote;
import com.diplom.spring_news_agregator.newsjobs.ParseTask;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Parser {

    private static final Logger LOGGER = LogManager.getLogger(Parser.class);

    public static void main(String[] args) throws IOException {

        String url = "https://quotes.toscrape.com/";

        Document doc = Jsoup.connect(url)
                .userAgent("Chrome/124.0.6367.119")
                .timeout(5000)
                .referrer("https://google.com")
                .get(); // метод

       /* Elements divs = doc.getElementsByTag("div");
         divs.forEach(System.out::println); // витягне все що є в тегах div
        String r = divs.text(); // витягне весь текст що є в тегах div
        LOGGER.info(r);  */

      /*  Elements quotes = doc.getElementsByClass("quote");
        for (Element quote : quotes) {
            LOGGER.info(quote.text()); // витягне текст з тегу div з классом quote
        } */

        //   Element div = doc.getElementById("quote-1"); // по Id якщо є

        // selecting all HTML elements that have the "value" attribute
       // Elements htmlElements = doc.getElementsByAttribute("value");

        // selecting all HTML elements that contain the word "for"
      /*  Elements htmlElements = doc.getElementsContainingText("for");
        for (Element htmlElement : htmlElements) {
            LOGGER.info(htmlElement.text());
        } */

        // selecting all quote HTML elements

        List<Quote> quotes = new ArrayList<>();
        Elements quoteElements = doc.getElementsByClass("quote");
        for (Element quoteElement: quoteElements) {
            Quote quote = new Quote();
            Element text = quoteElement.select(".text").first();
            quote.setText(text.text());
          //  LOGGER.info(text.text());
            Element author = quoteElement.select(".author").first();
            quote.setAuthor(author.text());
          //  LOGGER.info(author.text());
            Elements tags = quoteElement.select(".tag");
            quote.setTags(tags.text());
           // LOGGER.info(tags.text());
            quotes.add(quote);
        }
        System.out.println(quotes);

    }
}


// https://ru-brightdata.com/blog/how-tos-ru/web-scraping-with-jsoup