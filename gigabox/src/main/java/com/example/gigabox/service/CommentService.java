package com.example.gigabox.service;

import com.example.gigabox.dto.Board;
import com.example.gigabox.dto.Comment;
import com.example.gigabox.dto.Qna;
import com.example.gigabox.repository.CommentRepository;
import com.example.gigabox.users.GigaUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;

    public void create(Qna qna, String content){
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setQna(qna);
        this.commentRepository.save(comment);
    }
    //댓글 작성하기
    //댓글 작성할 게시글(board)과 댓글 내용(content)을 매개변수로 전달받음
    public void create(Qna qna, String content, GigaUser user){
        //Comment클래스로 comment인스턴스 생성
        Comment comment = new Comment();
        //comment인스턴스에 댓글 내용 세팅
        comment.setContent(content);
        //comment가 작성되는 게시글도 comment에 셋팅해야함
        comment.setQna(qna);
        //comment를 작성하는 사용자 아이디도 세팅
        comment.setAuthor(user);
        //DB에 comment 테이블에 게시글id와 댓글이 저장 (manytoone, onetomany 설정해놨기때문에, 1개의 게시글에 여러개의 댓글이 줄줄이 달리는 것!)
        this.commentRepository.save(comment);
    }

    //댓글 수정하기
    public Comment getOne(Long id){
        //매개변수로 전달받은 id값에 해당하는 레코드를 가져와서 comment인스턴스에 저장
        Optional<Comment> comment = this.commentRepository.findById(id);
        //commnet인스턴스에 들어있는 댓글 내용을 메서드 호출한 곳으로 반환
        return comment.get();
    }

    //댓글 삭제하기
    public void delete(Comment comment){
        this.commentRepository.delete(comment);
    }

}
