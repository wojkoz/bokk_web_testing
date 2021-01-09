package bookweb.domain.mapper;

import bookweb.domain.converter.Converter;
import bookweb.domain.dto.CreateBookDto;
import bookweb.domain.entity.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapper implements Converter<CreateBookDto, Book> {

    @Override
    public Book convert(CreateBookDto createBookDto) {
        Book book = new Book();
        book.setTitle(createBookDto.getTitle());
        book.setAuthor(createBookDto.getAuthor());
        book.setCover(createBookDto.getCover());
        book.setYear(createBookDto.getYear());
        book.setPublisher(createBookDto.getPublisher());

        return book;
    }
}
