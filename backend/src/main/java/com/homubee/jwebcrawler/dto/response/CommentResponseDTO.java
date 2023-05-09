package com.homubee.jwebcrawler.dto.response;

import com.homubee.jwebcrawler.domain.Comment;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentResponseDTO {
    private Long id;
    private String memberNickname;
    private String content;

    public CommentResponseDTO(Comment comment) {
        this.id = comment.getId();
        this.memberNickname = comment.getMember().getNickname();
        this.content = comment.getContent();
    }
}
