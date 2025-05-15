package cz.uhk.pro2_e.service;

import cz.uhk.pro2_e.model.Quote;
import cz.uhk.pro2_e.repository.QuoteRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class QuoteServiceImpl implements QuoteService {

    private final QuoteRepository quoteRepository;

    public QuoteServiceImpl(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    @Override
    public List<Quote> getAllQuotes() {
        return quoteRepository.findAll();
    }

    @Override
    public void saveQuote(Quote quote) {
        quoteRepository.save(quote);
    }

    @Override
    public Quote getQuote(long id) {
        return quoteRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteQuote(long id) {
        quoteRepository.deleteById(id);
    }
}