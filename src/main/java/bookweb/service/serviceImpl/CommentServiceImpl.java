package bookweb.service.serviceImpl;

import bookweb.domain.converter.Converter;
import bookweb.domain.dto.CommentDto;
import bookweb.domain.dto.CreateCommentDto;
import bookweb.domain.entity.Comment;
import bookweb.domain.repository.CommentRepository;
import bookweb.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final Converter<List<Comment>, List<CommentDto>> commentListMapper;
    private final Converter<CreateCommentDto, Comment> commentMapper;
    private final Converter<Comment, CommentDto> commentDtoMapper;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository,
                              Converter<List<Comment>, List<CommentDto>> commentListMapper,
                              Converter<CreateCommentDto, Comment> commentMapper,
                              Converter<Comment, CommentDto> commentDtoMapper) {
        this.commentRepository = commentRepository;
        this.commentListMapper = commentListMapper;
        this.commentMapper = commentMapper;
        this.commentDtoMapper = commentDtoMapper;
    }

    @Override
    public List<CommentDto> findAll() {
        return commentListMapper.convert(commentRepository.findAll());
    }

    @Override
    public void createComment(CreateCommentDto createCommentDto) {
        Comment comment = commentMapper.convert(createCommentDto);
        commentRepository.save(comment);
    }

    @Override
    public Optional<CommentDto> getById(Long id) {
        Optional<Comment> comment = commentRepository.findById(id);
        return comment.map(commentDtoMapper::convert);
    }

    @Override
    public Optional<CommentDto> deleteById(Long id) {
        Optional<Comment> comment = commentRepository.findById(id);
        if(comment.isPresent()){
            commentRepository.deleteById(id);
            return Optional.of(commentDtoMapper.convert(comment.get()));
        }else{
            return Optional.empty();
        }
    }

    @Override
    public List<CommentDto> getAllCommentsByBookId(Long id) {
        List<Comment> comments = commentRepository.getAllByBookId(id);
        return commentListMapper.convert(comments);

    }

    @Override
    public List<CommentDto> getAllCommentsByUserId(Long id) {
        List<Comment> comments = commentRepository.getAllByUserId(id);
        return commentListMapper.convert(comments);
    }
}
