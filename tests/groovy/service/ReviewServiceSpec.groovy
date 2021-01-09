package service

import bookweb.domain.dto.CreateReviewDto
import bookweb.domain.dto.ReviewDto
import bookweb.domain.mapper.ReviewDtoMapper
import bookweb.domain.mapper.ReviewListMapper
import bookweb.domain.mapper.ReviewMapper
import bookweb.domain.repository.ReviewRepository
import bookweb.service.ReviewService
import bookweb.service.serviceImpl.ReviewServiceImpl
import domain.respository.FailedReviewRepository
import spock.lang.Specification

class ReviewServiceSpec extends Specification{
    ReviewMapper reviewMapper = new ReviewMapper()
    ReviewListMapper listMapper = new ReviewListMapper()
    ReviewDtoMapper dtoMapper = new ReviewDtoMapper()
    ReviewRepository reviewRepository
    ReviewService service

    List<Long> reviewsId

    def setup(){
        CreateReviewDto reviewDto = new CreateReviewDto();
        reviewDto.setUserId(1)
        reviewDto.setBookId(1)
        reviewDto.setReview("bardzo nudna ksiazka")

        CreateReviewDto reviewDto2 = new CreateReviewDto();
        reviewDto2.setUserId(2)
        reviewDto2.setBookId(2)
        reviewDto2.setReview("bardzo ciekawa ksiazka")

        reviewsId = new ArrayList<>()

        reviewRepository = new FailedReviewRepository()

        reviewsId.add(reviewRepository.save(reviewMapper.convert(reviewDto)).getReviewId())
        reviewsId.add(reviewRepository.save(reviewMapper.convert(reviewDto2)).getReviewId())

        service = new ReviewServiceImpl(reviewMapper, listMapper, dtoMapper,reviewRepository)
    }

    def "should create new review"(){
        given: "1 new review"
        CreateReviewDto reviewDto = new CreateReviewDto();
        reviewDto.setUserId(1)
        reviewDto.setBookId(1)
        reviewDto.setReview("bardzo nudna ksiazka")

        when: "createReview method runs"
        List<ReviewDto> dtoList = service.createReview(reviewDto)

        then: "should return new review"
        dtoList.size() > 0
    }

    def "should delete review by id"(){
        given:" 2 reviews in base"
        Long idToDelete = reviewsId.get(0)

        when: "deleteReviewById method runs"
        Optional<ReviewDto> reviewDto = service.deleteReviewById(idToDelete)

        then: "review is deleted from repository"
        reviewDto.isPresent()
        reviewDto.get().getReviewId() == idToDelete
        reviewRepository.findAll().size() == 1

    }

    def "should return all reviews by user id"(){
        given: "user id"
        Long userId = 2L

        when:"getAllReviewsByUserId method runs"
        List<ReviewDto> dtoList = service.getAllReviewsByUserId(userId)

        then: "list size should be equal to 1"
        dtoList.size() == 1
        dtoList.get(0).getUserId() == userId

    }

    def "should return all reviews by book id"(){
        given: "book id"
        Long bookId = 1L

        when:"getAllReviewsByBookId method runs"
        List<ReviewDto> dtoList = service.getAllReviewsByBookId(bookId)

        then: "list size should be equal to 1"
        dtoList.size() == 1
        dtoList.get(0).getBookId() == bookId

    }

    def "should return review by id"(){
        given: "review id"
        Long reviewId = reviewsId.get(1)

        when: "getReviewById method runs"
        Optional<ReviewDto> reviewById = service.getReviewById(reviewId)

        then: "review should have return"
        reviewById.isPresent()
        reviewById.get().getReviewId() == reviewId
    }

    def "shouldn't return review with wrong id"(){
        given: "review id"
        Long reviewId = 4099L

        when: "getReviewById method runs"
        Optional<ReviewDto> reviewById = service.getReviewById(reviewId)

        then: "review should have return"
        !reviewById.isPresent()
    }
}
