package service

import bookweb.domain.dto.BookDto
import bookweb.domain.dto.CommentDto
import bookweb.domain.dto.CreateBookDto
import bookweb.domain.entity.Book
import bookweb.domain.mapper.BookDtoMapper
import bookweb.domain.mapper.BookListMapper
import bookweb.domain.mapper.BookMapper
import bookweb.domain.repository.BookRepository
import bookweb.service.BookService
import bookweb.service.serviceImpl.BookServiceImpl
import domain.respository.FailedBookRepository
import spock.lang.Specification

class BookServiceSpec extends Specification{
    BookMapper bookMapper = new BookMapper()
    BookListMapper bookListMapper = new BookListMapper()
    BookDtoMapper bookDtoMapper = new BookDtoMapper()
    BookRepository bookRepository
    BookService bookService

    def setup(){
        bookRepository = new FailedBookRepository()
        bookService = new BookServiceImpl(bookRepository, bookListMapper, bookMapper, bookDtoMapper)
        CreateBookDto bookDto = new CreateBookDto()
        bookDto.setAuthor('baba jaga')
        bookDto.setCover('http://jakislink.pl')
        bookDto.setPublisher('Jasiek Zbylitowski')
        bookDto.setTitle('Jak pisac testy')
        bookDto.setYear(2020)

        CreateBookDto bookDto2 = new CreateBookDto()
        bookDto2.setAuthor('a taki sobie autor')
        bookDto2.setCover('http://fajnylink.pl')
        bookDto2.setPublisher('Taki wydawca fajny')
        bookDto2.setTitle('Jak pisac testy2')
        bookDto2.setYear(2021)

        bookService.createBook(bookDto)
        bookService.createBook(bookDto2)
    }

    def "should return all books"(){
        when: "findAll method runs"
        List<BookDto> bookDtoList = bookService.findAll()
        println bookDtoList

        then: "should return 2 books"
        bookDtoList.size() == 2
    }
}
