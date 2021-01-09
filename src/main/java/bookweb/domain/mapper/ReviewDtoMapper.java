package bookweb.domain.mapper;

import bookweb.domain.converter.Converter;
import bookweb.domain.dto.ReviewDto;
import bookweb.domain.entity.Review;
import org.springframework.stereotype.Component;

@Component
public class ReviewDtoMapper implements Converter<Review, ReviewDto> {
    @Override
    public ReviewDto convert(Review from) {
        ReviewDto reviewDto = new ReviewDto();

        reviewDto.setBookId(from.getBookId());
        reviewDto.setReview(from.getReview());
        reviewDto.setReviewId(from.getReviewId());
        reviewDto.setUserId(from.getUserId());

        return reviewDto;
    }
}
