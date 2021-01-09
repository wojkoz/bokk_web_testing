package domain.respository;

import bookweb.domain.entity.Review;
import bookweb.domain.repository.ReviewRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.*;

public class FailedReviewRepository implements ReviewRepository {

    Map<Long, Review> reviews = new HashMap<>();

    @Override
    public List<Review> getAllByBookIdEquals(Long id) {
        List<Review> reviewList = new ArrayList<>();

        reviews.forEach((key, value) -> {
            if(value.getBookId().equals(id)){
                reviewList.add(value);
            }
        });

        return reviewList;
    }

    @Override
    public List<Review> getAllByUserId(Long id) {
        List<Review> reviewList = new ArrayList<>();

        reviews.forEach((key, value) -> {
            if(value.getUserId().equals(id)){
                reviewList.add(value);
            }
        });

        return reviewList;
    }

    @Override
    public List<Review> findAll() {
        return new ArrayList<>(reviews.values());
    }

    @Override
    public List<Review> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Review> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Review> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {
        reviews.remove(aLong);
    }

    @Override
    public void delete(Review entity) {

    }

    @Override
    public void deleteAll(Iterable<? extends Review> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Review> S save(S entity) {
        Long id = new Random().nextLong();
        entity.setReviewId(id);
        reviews.put(id, entity);
        return entity;
    }

    @Override
    public <S extends Review> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Review> findById(Long aLong) {

        return Optional.ofNullable(reviews.get(aLong));
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Review> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<Review> entities) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Review getOne(Long aLong) {
        return null;
    }

    @Override
    public <S extends Review> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Review> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Review> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Review> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Review> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Review> boolean exists(Example<S> example) {
        return false;
    }
}
