package cz.uhk.pro2_e.service;

import cz.uhk.pro2_e.model.Review;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface ReviewService {
    List<Review> getAllReviews();
    void saveReview(Review review);
    Review getReview(long id);
    void deleteReview(long id);
}
