package com.diplom.spring_news_agregator.controller;

import com.diplom.spring_news_agregator.model.News;
import com.diplom.spring_news_agregator.service.NewsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NewsController {

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    NewsService newsService;

    @GetMapping("/news")
    public List<News> getAllNews() {
        return newsService.getAllNews();
    }


}
