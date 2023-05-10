package com.homubee.jwebcrawler.dto.response;

import com.homubee.jwebcrawler.domain.Comment;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Getter
@Setter
public class CommentResponseDTO {
    private Long id;
    private MemberResponseDTO member;
    private String content;

    public CommentResponseDTO(Comment comment) {
        this.id = comment.getId();
        this.member = new ModelMapper().map(comment.getMember(), MemberResponseDTO.class);
        this.content = comment.getContent();
    }
}
