package bookweb.service.serviceImpl;

import bookweb.domain.converter.Converter;
import bookweb.domain.dto.CreateReviewDto;
import bookweb.domain.dto.ReviewDto;
import bookweb.domain.entity.Review;
import bookweb.domain.repository.ReviewRepository;
import bookweb.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final Converter<CreateReviewDto, Review> reviewMapper;
    private final Converter<List<Review>, List<ReviewDto>> reviewListMapper;
    private final Converter<Review, ReviewDto> reviewDtoMapper;
    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewServiceImpl(Converter<CreateReviewDto, Review> reviewMapper,
                             Converter<List<Review>, List<ReviewDto>> reviewListMapper,
                             Converter<Review, ReviewDto> reviewDtoMapper,
                             ReviewRepository reviewRepository) {
        this.reviewMapper = reviewMapper;
        this.reviewListMapper = reviewListMapper;
        this.reviewDtoMapper = reviewDtoMapper;
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<ReviewDto> getAllReviewsByBookId(Long id) {

        return reviewListMapper.convert(reviewRepository.getAllByBookIdEquals(id));
    }

    @Override
    public Optional<ReviewDto> getReviewById(Long id) {
        Optional<Review> review = reviewRepository.findById(id);
        return review.map(reviewDtoMapper::convert);
    }

    @Override
    public List<ReviewDto> getAllReviewsByUserId(Long id) {

        return reviewListMapper.convert(reviewRepository.getAllByUserId(id));
    }

    @Override
    public Optional<ReviewDto> deleteReviewById(Long id) {
        Optional<Review> review = reviewRepository.findById(id);
        if(review.isPresent()){
            reviewRepository.deleteById(id);
            return Optional.of(reviewDtoMapper.convert(review.get()));
        }else{
            return Optional.empty();
        }

    }

    @Override
    public List<ReviewDto> createReview(CreateReviewDto createReviewDto) {
        Review review = reviewMapper.convert(createReviewDto);
        reviewRepository.save(review);
        return null;
    }
}
