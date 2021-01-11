package bookweb.controller;

import bookweb.domain.dto.CommentDto;
import bookweb.domain.dto.CreateCommentDto;
import bookweb.service.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/api")
public class CommentApiController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommentApiController.class);

    private final CommentService commentService;

    @Autowired
    public CommentApiController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping(value = "/comments", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<CommentDto>> getComments() {
        LOGGER.info("find all comments");

        List<CommentDto> commentDtoList = commentService.findAll();
        return new ResponseEntity<>(commentDtoList, HttpStatus.OK);
    }

    @PostMapping(value = "/comments", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Void> createComment(@RequestBody CreateCommentDto createCommentDto) {
        LOGGER.info("create comment: {}", createCommentDto);

        commentService.createComment(createCommentDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/comments/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<CommentDto> getCommentById(@PathVariable Long id) {
        LOGGER.info("request comment with id: {}",id);

        Optional<CommentDto> commentDto = commentService.getById(id);
        return commentDto.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NO_CONTENT));
    }

    @DeleteMapping(value = "/comments/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<CommentDto> deleteComment(@PathVariable Long id) {
        LOGGER.info("delete comment with id: {}",id);

        Optional<CommentDto> commentDto = commentService.deleteById(id);
        return commentDto.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(new CommentDto(), HttpStatus.NO_CONTENT));
    }

    @GetMapping(value = "/comments/user/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<CommentDto>> getUserComments(@PathVariable Long id) {
        LOGGER.info("get comments with user id: {}",id);

        List<CommentDto> commentDto = commentService.getAllCommentsByUserId(id);
        return  new ResponseEntity<>(commentDto, HttpStatus.OK);
    }

    @GetMapping(value = "/comments/book/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<CommentDto>> getBookComments(@PathVariable Long id) {
        LOGGER.info("get comments with book id: {}",id);

        List<CommentDto> commentDto = commentService.getAllCommentsByBookId(id);
        return  new ResponseEntity<>(commentDto, HttpStatus.OK);
    }
}
