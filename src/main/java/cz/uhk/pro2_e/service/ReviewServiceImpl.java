package cz.uhk.pro2_e.service;

import cz.uhk.pro2_e.model.Review;
import cz.uhk.pro2_e.repository.ReviewRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    @Override
    public void saveReview(Review review) {
        reviewRepository.save(review);
    }

    @Override
    public Review getReview(long id) {
        return reviewRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteReview(long id) {
        reviewRepository.deleteById(id);
    }
}