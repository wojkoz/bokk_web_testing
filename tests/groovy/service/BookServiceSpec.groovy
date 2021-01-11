package service

import bookweb.domain.dto.BookDto
import bookweb.domain.dto.CreateBookDto
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
    ArrayList<Long> booksId = new ArrayList<>()

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

        BookDto newBook = bookService.createBook(bookDto)
        BookDto newBook2 = bookService.createBook(bookDto2)

        booksId.push(newBook.getBookId())
        booksId.push(newBook2.getBookId())
    }

    def "should return all books"(){
        when: "findAll method runs"
        List<BookDto> bookDtoList = bookService.findAll()

        then: "should return 2 books"
        bookDtoList.size() == 2
    }

    def "should create new book"(){
        given: "new book"
        CreateBookDto bookDto = new CreateBookDto()
        bookDto.setAuthor('nowa ksiazka')
        bookDto.setCover('http://nowaksiazka.pl')
        bookDto.setPublisher('Jasiek Nowa ksiazka')
        bookDto.setTitle('Jak pisac testy3')
        bookDto.setYear(2022)

        when: "createBook method runs"
        bookService.createBook(bookDto)

        then: "should return 3 books"
        bookService.findAll().size() == 3
    }

    def "should return book by given id"(){
        given: "new book"
        CreateBookDto bookDto = new CreateBookDto()
        bookDto.setAuthor('nowa ksiazka po id')
        bookDto.setCover('http://nowaksiazkapoid.pl')
        bookDto.setPublisher('Jasiek Nowa ksiazka po id')
        bookDto.setTitle('Jak pisac testy4')
        bookDto.setYear(2023)
        BookDto newBook = bookService.createBook(bookDto)

        when: "findBookById method runs"

        Optional<BookDto> findBook = bookService.findById(newBook.getBookId())

        then: "should return book"
        findBook.isPresent()
        findBook.get().getBookId() == newBook.getBookId()
    }

    def "should delete book by id"(){
        given: "book id"
        Long bookId = booksId[0]

        when: "deleteById method runs"

        bookService.deleteById(bookId)

        then: "should delete book"

        bookService.findAll().size() == 1
    }

    def "should search for book"(){
        given: "any letter"
        String findBook = 'Jak pisac testy2'

        when: "searchBook method runs"

        List<BookDto> bookDtoList = bookService.searchBook(findBook)

        then: "should return list of books that match string"

        bookDtoList.size() == 1
        bookDtoList[0].getTitle() == findBook
    }
}
