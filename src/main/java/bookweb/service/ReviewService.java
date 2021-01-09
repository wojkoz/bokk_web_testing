package bookweb.service;

import bookweb.domain.dto.CreateReviewDto;
import bookweb.domain.dto.ReviewDto;

import java.util.List;
import java.util.Optional;

public interface ReviewService {
    List<ReviewDto> getAllReviewsByBookId(Long id);
    Optional<ReviewDto> getReviewById(Long id);
    List<ReviewDto> getAllReviewsByUserId(Long id);
    Optional<ReviewDto> deleteReviewById(Long id);
    List<ReviewDto> createReview(CreateReviewDto createReviewDto);
}
