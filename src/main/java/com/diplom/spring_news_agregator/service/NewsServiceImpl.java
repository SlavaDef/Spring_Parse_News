package com.diplom.spring_news_agregator.service;

import com.diplom.spring_news_agregator.model.News;
import com.diplom.spring_news_agregator.repo.NewsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

    public NewsServiceImpl(NewsRepository repository) {
        this.repository = repository;
    }

    // @Autowired
    NewsRepository repository;

    @Override
    public void save(News news) {
        repository.save(news);
    }

    @Override
    public boolean isExist(String newsTitle) {
        List<News> newsList = repository.findAll();
        for (News n : newsList) {
            if (n.getTitle().equals(newsTitle)) {
               return true;
            }
        }
        return false;
    }

    @Override
    public List<News> getAllNews() {
        return repository.findAll();
    }
}
