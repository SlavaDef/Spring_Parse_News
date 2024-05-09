package com.diplom.spring_news_agregator.controller;

import com.diplom.spring_news_agregator.model.Quote;
import com.diplom.spring_news_agregator.service.QuoteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

public class QuoteController {

    public QuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    QuoteService quoteService;

    @GetMapping("/quote")
    public List<Quote> getQuote() {
        return quoteService.getAllQ();
    }
}
