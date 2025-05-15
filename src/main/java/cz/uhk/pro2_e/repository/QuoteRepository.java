package cz.uhk.pro2_e.repository;

import cz.uhk.pro2_e.model.Quote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuoteRepository extends JpaRepository<Quote, Long> {}
