package com.diplom.spring_news_agregator.repo;

import com.diplom.spring_news_agregator.model.News;
import com.diplom.spring_news_agregator.model.Quote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuoteRepo extends JpaRepository<Quote, Long> {
}
