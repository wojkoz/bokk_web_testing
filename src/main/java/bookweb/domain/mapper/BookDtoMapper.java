package bookweb.domain.mapper;

import bookweb.domain.converter.Converter;
import bookweb.domain.dto.BookDto;
import bookweb.domain.entity.Book;
import org.springframework.stereotype.Component;

@Component
public class BookDtoMapper implements Converter<Book, BookDto> {
    @Override
    public BookDto convert(Book from) {
        BookDto bookDto = new BookDto();

        bookDto.setAuthor(from.getAuthor());
        bookDto.setBookId(from.getBookId());
        bookDto.setCover(from.getCover());
        bookDto.setPublisher(from.getPublisher());
        bookDto.setTitle(from.getTitle());
        bookDto.setYear(from.getYear());

        return bookDto;
    }
}
