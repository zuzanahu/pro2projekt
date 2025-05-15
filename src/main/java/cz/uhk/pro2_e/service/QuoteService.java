package cz.uhk.pro2_e.service;

import cz.uhk.pro2_e.model.Quote;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface QuoteService {
    List<Quote> getAllQuotes();
    void saveQuote(Quote quote);
    Quote getQuote(long id);
    void deleteQuote(long id);
}