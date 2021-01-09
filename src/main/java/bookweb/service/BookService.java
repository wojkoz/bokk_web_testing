package bookweb.service;

import bookweb.domain.dto.BookDto;
import bookweb.domain.dto.CreateBookDto;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<BookDto> findAll();
    void createBook(CreateBookDto createBookDto);
    Optional<BookDto> findById(Long bookId);
    Optional<BookDto> deleteById(Long bookId);
    List<BookDto> searchBook(String book);
}
