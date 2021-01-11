package service

import bookweb.domain.dto.CommentDto
import bookweb.domain.dto.CreateCommentDto
import bookweb.domain.mapper.CommentDtoMapper
import bookweb.domain.mapper.CommentListMapper
import bookweb.domain.mapper.CommentMapper
import bookweb.domain.repository.CommentRepository
import bookweb.service.CommentService
import bookweb.service.serviceImpl.CommentServiceImpl
import domain.respository.FailedCommentRepository
import spock.lang.Specification

class CommentServiceSpec extends Specification{

    CommentMapper commentMapper = new CommentMapper()
    CommentListMapper commentListMapper = new CommentListMapper()
    CommentDtoMapper commentDtoMapper = new CommentDtoMapper()
    CommentRepository commentRepository
    CommentService commentService

    List<Long> commentsId

    def setup(){
        CreateCommentDto commentDto = new CreateCommentDto()
        commentDto.setUserId(1)
        commentDto.setBookId(1)
        commentDto.setComment("fajny komentarz")

        CreateCommentDto commentDto2 = new CreateCommentDto();
        commentDto2.setUserId(2)
        commentDto2.setBookId(2)
        commentDto2.setComment("bardzo ciekawy komentarz")

        commentsId = new ArrayList<>()

        commentRepository = new FailedCommentRepository()

        commentsId.add(commentRepository.save(commentMapper.convert(commentDto)).getCommentId())
        commentsId.add(commentRepository.save(commentMapper.convert(commentDto2)).getCommentId())

        commentService = new CommentServiceImpl(commentRepository, commentListMapper, commentMapper, commentDtoMapper)
    }

    def "should create new comment"(){
        given: "new comment"
        CreateCommentDto commentDto = new CreateCommentDto();
        commentDto.setUserId(1)
        commentDto.setBookId(1)
        commentDto.setComment("bardzo fajny komentarz")

        when: "createComment method runs"
        ArrayList<CommentDto> commentDtoList = new ArrayList<>()
        commentDtoList.push(commentService.createComment(commentDto))

        then: "should return new comment"
        commentDtoList.size() > 0
    }


    def "should return comment by id"(){
        given: "comment id"
        Long commentId = commentsId.get(1)

        when: "getById method runs"
        Optional<CommentDto> commentDto = commentService.getById(commentId)

        then: "should return comment"
        commentDto.isPresent()
        commentDto.get().getCommentId() == commentId
    }

    def "should not return comment"(){
        given: "wrong comment id"
        Long commentId = 4099L

        when: "getById method runs"
        Optional<CommentDto> commentDto = commentService.getById(commentId)

        then: "comment should have return"
        !commentDto.isPresent()
    }

    def "should delete comment by id"(){
        given: "comment"
        Long idToDelete = commentsId.get(0)

        when: "deleteById method runs"
        Optional<CommentDto> commentDto = commentService.deleteById(idToDelete)

        then: "comment is deleted from repository"
        commentDto.isPresent()
        commentDto.get().getCommentId() == idToDelete
        commentDto.findAll().size() == 1

    }

    def "should return all comments by user id"(){
        given: "user id"
        Long userId = 2L

        when: "getAllCommentsByUserId method runs"
        List<CommentDto> commentDtoList = commentService.getAllCommentsByUserId(userId)

        then: "list size should be equal to 1"
        commentDtoList.size() == 1
        commentDtoList.get(0).getUserId() == userId

    }

    def "should return all comments by book id"(){
        given: "book id"
        Long bookId = 1L

        when:"getAllCommentsByBookId method runs"
        List<CommentDto> commentDtoList = commentService.getAllCommentsByBookId(bookId)

        then: "list size should be equal to 1"
        commentDtoList.size() == 1
        commentDtoList.get(0).getBookId() == bookId

    }

}
