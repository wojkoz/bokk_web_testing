package bookweb.domain.repository;

import bookweb.domain.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>, CrudRepository<Book, Long> {
    @Query("SELECT m FROM Book m WHERE LOWER(m.title) LIKE TRIM(LOWER(CONCAT('%',TRIM(:book),'%')))")
    List<Book> searchBook(String book);
}
