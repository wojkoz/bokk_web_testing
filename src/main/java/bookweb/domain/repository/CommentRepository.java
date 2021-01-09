package bookweb.domain.repository;

import bookweb.domain.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long>, CrudRepository<Comment, Long> {
    List<Comment> getAllByBookId(Long id);
    List<Comment> getAllByUserId(Long id);
}
