package com.homubee.jwebcrawler.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="COMMENT")
@SequenceGenerator(
        name = "COMMENT_SEQ_GENERATOR",
        sequenceName = "COMMENT_SEQ",
        initialValue = 1, allocationSize = 1)
@Getter
@Setter
public class Comment extends BaseEntity {
    @Id
    @GeneratedValue(generator = "COMMENT_SEQ_GENERATOR")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="MEMBER_ID")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="POST_ID")
    private Post post;

    private String content;
}
