package bookweb.domain.mapper;

import bookweb.domain.converter.Converter;
import bookweb.domain.dto.CreateReviewDto;
import bookweb.domain.entity.Review;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapper implements Converter<CreateReviewDto, Review> {
    @Override
    public Review convert(CreateReviewDto from) {
        Review review = new Review();

        review.setBookId(from.getBookId());
        review.setReview(from.getReview());
        review.setUserId(from.getUserId());

        return review;
    }
}
