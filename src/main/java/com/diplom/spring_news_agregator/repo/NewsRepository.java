package com.diplom.spring_news_agregator.repo;

import com.diplom.spring_news_agregator.model.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
}
