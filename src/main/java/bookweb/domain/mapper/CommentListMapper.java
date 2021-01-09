package bookweb.domain.mapper;

import bookweb.domain.converter.Converter;
import bookweb.domain.dto.CommentDto;
import bookweb.domain.entity.Comment;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CommentListMapper implements Converter<List<Comment>, List<CommentDto>> {
    @Override
    public List<CommentDto> convert(List<Comment> from) {
        return from.stream().map(comment -> {
            CommentDto commentDto = new CommentDto();

            commentDto.setBookId(comment.getBookId());
            commentDto.setComment(comment.getComment());
            commentDto.setCommentId(comment.getCommentId());
            commentDto.setUserId(comment.getUserId());

            return commentDto;
        }).collect(Collectors.toList());
    }
}
