package bookweb.domain.dto;

import java.io.Serializable;

public class BookDto implements Serializable {
    private Long bookId;
    private String title;
    private String author;
    private Integer year;
    private String publisher;
    private String cover;

    public BookDto() {

    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getCover() {
        return cover;
    }

    public String getPublisher() {
        return publisher;
    }

    public Integer getYear() {
        return year;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public Long getBookId() {
        return bookId;
    }
}
