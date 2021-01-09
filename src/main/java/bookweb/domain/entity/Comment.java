package bookweb.domain.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "comments")
public class Comment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comments")
    private Long commentId;

    @Column(name = "id_book")
    private Long bookId;

    @Column(name = "id_user")
    private Long userId;

    @Column(name = "comment")
    private String comment;

    public Comment() {

    }

    public Long getCommentId() {
        return commentId;
    }

    public Long getBookId() {
        return bookId;
    }

    public Long getUserId() {
        return userId;
    }

    public String getComment() {
        return comment;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
