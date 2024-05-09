package com.diplom.spring_news_agregator.service;

import com.diplom.spring_news_agregator.model.Quote;

import java.util.List;

public interface QuoteService {

    void save(Quote quote);

    List<Quote> getAllQ();

    void parserin();

}
