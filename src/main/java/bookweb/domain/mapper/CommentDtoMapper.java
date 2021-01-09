package bookweb.domain.mapper;

import bookweb.domain.converter.Converter;
import bookweb.domain.dto.CommentDto;
import bookweb.domain.entity.Comment;
import org.springframework.stereotype.Component;

@Component
public class CommentDtoMapper implements Converter<Comment, CommentDto> {
    @Override
    public CommentDto convert(Comment from) {
        CommentDto commentDto = new CommentDto();

        commentDto.setBookId(from.getBookId());
        commentDto.setComment(from.getComment());
        commentDto.setCommentId(from.getCommentId());
        commentDto.setUserId(from.getUserId());

        return commentDto;
    }
}
