package bookweb.controller;

import bookweb.domain.dto.CreateReviewDto;
import bookweb.domain.dto.ReviewDto;
import bookweb.service.ReviewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/api")
public class ReviewApiController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReviewApiController.class);

    private final ReviewService reviewService;

    public ReviewApiController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }


    @GetMapping(value = "/reviews/book/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<ReviewDto>> getReviewsByBookId(@PathVariable Long id) {
        LOGGER.info("find reviews by book id: {}", id);

        List<ReviewDto> reviewDtos = reviewService.getAllReviewsByBookId(id);
        return new ResponseEntity<>(reviewDtos, HttpStatus.OK);
    }

    @GetMapping(value = "/reviews/user/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<ReviewDto>> getReviewsByUserId(@PathVariable Long id) {
        LOGGER.info("find reviews by user id: {}", id);

        List<ReviewDto> reviewDtos = reviewService.getAllReviewsByUserId(id);
        return new ResponseEntity<>(reviewDtos, HttpStatus.OK);
    }

    @GetMapping(value = "/reviews/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ReviewDto> getReviewsById(@PathVariable Long id) {
        LOGGER.info("find reviews by id: {}", id);

        Optional<ReviewDto> reviewDto = reviewService.getReviewById(id);
        return reviewDto.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(new ReviewDto(), HttpStatus.NO_CONTENT));

    }

    @PostMapping(value = "/reviews", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<ReviewDto>> createReview(@RequestBody CreateReviewDto createReviewDto) {
        LOGGER.info("create review: {}", createReviewDto);

        List<ReviewDto> review = reviewService.createReview(createReviewDto);
        return new ResponseEntity<>(review, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/reviews/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ReviewDto> deleteReview(@PathVariable("id") Long id) {
        LOGGER.info("delete review: {}", id);

        Optional<ReviewDto> review = reviewService.deleteReviewById(id);
        return review.map(reviewDto -> new ResponseEntity<>(reviewDto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(new ReviewDto(), HttpStatus.NO_CONTENT));

    }


}
