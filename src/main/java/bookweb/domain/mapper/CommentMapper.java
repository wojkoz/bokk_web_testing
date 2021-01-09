package bookweb.domain.mapper;

import bookweb.domain.converter.Converter;
import bookweb.domain.dto.CreateCommentDto;
import bookweb.domain.entity.Comment;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper implements Converter<CreateCommentDto, Comment> {
    @Override
    public Comment convert(CreateCommentDto from) {
        Comment comment = new Comment();

        comment.setBookId(from.getBookId());
        comment.setComment(from.getComment());
        comment.setUserId(from.getUserId());

        return comment;
    }
}
