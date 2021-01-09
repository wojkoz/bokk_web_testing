package bookweb.service.serviceImpl;

import bookweb.domain.converter.Converter;
import bookweb.domain.dto.BookDto;
import bookweb.domain.dto.CreateBookDto;
import bookweb.domain.entity.Book;
import bookweb.domain.repository.BookRepository;
import bookweb.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final Converter<List<Book>, List<BookDto>> bookListMapper;
    private final Converter<CreateBookDto, Book> bookMapper;
    private final Converter<Book, BookDto> bookDtoMapper;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository,
                           Converter<List<Book>, List<BookDto>> bookListMapper,
                           Converter<CreateBookDto, Book> bookMapper, Converter<Book, BookDto> bookDtoMapper) {
        this.bookRepository = bookRepository;
        this.bookListMapper = bookListMapper;
        this.bookMapper = bookMapper;
        this.bookDtoMapper = bookDtoMapper;
    }

    @Override
    public List<BookDto> findAll() {
        List<Book> books = bookRepository.findAll();
        books.sort(Comparator.comparing(Book::getBookId));
        return bookListMapper.convert(books);
    }

    @Override
    public void createBook(CreateBookDto createBookDto) {
        Book book = bookMapper.convert(createBookDto);
        bookRepository.save(book);
    }

    @Override
    public Optional<BookDto> findById(Long bookId) {
        Optional<Book> book = bookRepository.findById(bookId);
        if(book.isPresent()){
            return book.map(bookDtoMapper::convert);
        }else{
            return Optional.empty();
        }


    }

    @Override
    public Optional<BookDto> deleteById(Long bookId) {
        Optional<Book> book = bookRepository.findById(bookId);

        if(book.isPresent()){
            bookRepository.deleteById(bookId);
            return Optional.of(bookDtoMapper.convert(book.get()));
        }else{
            return Optional.empty();
        }
    }

    @Override
    public List<BookDto> searchBook(String book) {
        List<Book> bookDtoList = bookRepository.searchBook(book);
        return bookListMapper.convert(bookDtoList);
    }


}
