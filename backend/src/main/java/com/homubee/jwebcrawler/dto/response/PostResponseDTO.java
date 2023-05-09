package com.homubee.jwebcrawler.dto.response;

import com.homubee.jwebcrawler.domain.Comment;
import com.homubee.jwebcrawler.domain.Member;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class PostResponseDTO {
    private Long id;
    private Member member;
    private List<CommentResponseDTO> commentList = new ArrayList<>();
    private String title;
    private String content;
    private int viewCnt;

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList.stream().map(comment -> new CommentResponseDTO(comment)).collect(Collectors.toList());
    }
}
