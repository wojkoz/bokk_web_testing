package bookweb.domain.mapper;

import bookweb.domain.converter.Converter;
import bookweb.domain.dto.ReviewDto;
import bookweb.domain.entity.Review;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReviewListMapper implements Converter<List<Review>, List<ReviewDto>> {
    @Override
    public List<ReviewDto> convert(List<Review> from) {

        return from.stream().map(review -> {
            ReviewDto reviewDto = new ReviewDto();

            reviewDto.setBookId(review.getBookId());
            reviewDto.setReview(review.getReview());
            reviewDto.setReviewId(review.getReviewId());
            reviewDto.setUserId(review.getUserId());

            return reviewDto;
        }).collect(Collectors.toList());
    }
}
