package bookweb.controller;

import bookweb.domain.dto.BookDto;
import bookweb.domain.dto.CreateBookDto;
import bookweb.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/api")
@CrossOrigin(origins = "http://localhost:4200")
public class BookApiController {
    private static final Logger LOGGER = LoggerFactory.getLogger(BookApiController.class);

    private final BookService bookService;

    @Autowired
    public BookApiController(BookService bookService) {
        this.bookService = bookService;
    }

    @CrossOrigin
    @GetMapping(value = "/books", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<BookDto>> getBooks() {
        LOGGER.info("find all books");

        List<BookDto> bookDtoList = bookService.findAll();
        return new ResponseEntity<>(bookDtoList, HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping(value = "/books", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Void> createBook(@RequestBody CreateBookDto createBookDto) {
        LOGGER.info("create book: {}", createBookDto);

        bookService.createBook(createBookDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @CrossOrigin
    @GetMapping(value = "/books/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<BookDto> getBookById(@PathVariable Long id) {
        LOGGER.info("request book with id: {}",id);

        Optional<BookDto> bookDto = bookService.findById(id);
        return bookDto.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NO_CONTENT));
    }

    @CrossOrigin
    @DeleteMapping(value = "/books/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<BookDto> deleteBook(@PathVariable Long id) {
        LOGGER.info("delete book with id: {}",id);

        Optional<BookDto> bookDto = bookService.deleteById(id);
        return bookDto.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(new BookDto(), HttpStatus.NO_CONTENT));
    }

    @CrossOrigin
    @GetMapping(value = "/search-book/{searchBook}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<BookDto>> searchMovies(@PathVariable String searchBook) {
        LOGGER.info("search book by title");
        List<BookDto> bookSearch = bookService.searchBook(searchBook);
        return new ResponseEntity<>(bookSearch, HttpStatus.OK);
    }

}
