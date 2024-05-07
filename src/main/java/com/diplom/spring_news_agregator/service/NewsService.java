package com.diplom.spring_news_agregator.service;

import com.diplom.spring_news_agregator.model.News;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NewsService {

    void save(News news);

    boolean isExist(String newsTitle);

    List<News> getAllNews();


}
