package domain.respository;

import bookweb.domain.entity.Comment;
import bookweb.domain.repository.CommentRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.*;

public class FailedCommentRepository implements CommentRepository {

    Map<Long, Comment> comments = new HashMap<>();

    @Override
    public List<Comment> getAllByBookId(Long id) {
        List<Comment> commentList = new ArrayList<>();

        comments.forEach((key, value) -> {
            if(value.getBookId().equals(id)){
                commentList.add(value);
            }
        });

        return commentList;
    }

    @Override
    public List<Comment> getAllByUserId(Long id) {
        List<Comment> commentList = new ArrayList<>();

        comments.forEach((key, value) -> {
            if(value.getUserId().equals(id)){
                commentList.add(value);
            }
        });

        return commentList;
    }

    @Override
    public List<Comment> findAll() {
        return null;
    }

    @Override
    public List<Comment> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Comment> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Comment> findAllById(Iterable<Long> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {
        comments.remove(aLong);
    }

    @Override
    public void delete(Comment comment) {

    }

    @Override
    public void deleteAll(Iterable<? extends Comment> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Comment> S save(S s) {
        Long id = new Random().nextLong();
        s.setCommentId(id);
        comments.put(id, s);
        return s;
    }

    @Override
    public <S extends Comment> List<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<Comment> findById(Long aLong) {
        return Optional.ofNullable(comments.get(aLong));
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Comment> S saveAndFlush(S s) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<Comment> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Comment getOne(Long aLong) {
        return null;
    }

    @Override
    public <S extends Comment> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Comment> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Comment> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Comment> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Comment> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Comment> boolean exists(Example<S> example) {
        return false;
    }
}
