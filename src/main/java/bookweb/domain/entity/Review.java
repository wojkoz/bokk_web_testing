package bookweb.domain.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "reviews")
public class Review implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_review")
    private Long reviewId;

    @Column(name = "id_book")
    private Long bookId;

    @Column(name = "id_user")
    private Long userId;

    @Column(name = "review")
    private String review;

    public Review() {

    }

    public Long getReviewId() {
        return reviewId;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public void setReviewId(Long reviewId) {
        this.reviewId = reviewId;
    }

    public String getReview() {
        return review;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getBookId() {
        return bookId;
    }
}
