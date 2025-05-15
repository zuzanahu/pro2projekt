package cz.uhk.pro2_e.repository;

import cz.uhk.pro2_e.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {}

