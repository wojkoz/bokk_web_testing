package bookweb.domain.mapper;

import bookweb.domain.converter.Converter;
import bookweb.domain.dto.BookDto;
import bookweb.domain.entity.Book;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookListMapper implements Converter<List<Book>, List<BookDto>> {
    @Override
    public List<BookDto> convert(List<Book> from) {
        return from.stream().map(
                book -> {
                    BookDto bookDto = new BookDto();

                    bookDto.setBookId(book.getBookId());
                    bookDto.setTitle(book.getTitle());
                    bookDto.setAuthor(book.getAuthor());
                    bookDto.setYear(book.getYear());
                    bookDto.setPublisher(book.getPublisher());
                    bookDto.setCover(book.getCover());

                    return bookDto;
                }
        ).collect(Collectors.toList());

    }
}
