package bookweb.domain.repository;

import bookweb.domain.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long>, CrudRepository<Review, Long> {
    List<Review> getAllByBookIdEquals(Long id);
    List<Review> getAllByUserId(Long id);
}
