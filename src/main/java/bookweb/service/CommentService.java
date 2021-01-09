package bookweb.service;

import bookweb.domain.dto.CommentDto;
import bookweb.domain.dto.CreateCommentDto;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    List<CommentDto> findAll();
    void createComment(CreateCommentDto createCommentDto);
    Optional<CommentDto> getById(Long id);
    Optional<CommentDto> deleteById(Long id);
    List<CommentDto> getAllCommentsByBookId(Long id);
    List<CommentDto> getAllCommentsByUserId(Long id);
}
