package com.homubee.jwebcralwer.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="POST")
@SequenceGenerator(
        name = "POST_SEQ_GENERATOR",
        sequenceName = "POST_SEQ",
        initialValue = 1, allocationSize = 1)
@Getter
@Setter
public class Post extends BaseEntity {
    @Id
    @GeneratedValue(generator = "POST_SEQ_GENERATOR")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="MEMBER_ID")
    private Member member;

    @OneToMany
    @JoinColumn(name = "POST_ID")
    private List<Comment> commentList = new ArrayList<>();

    private String title;

    private String content;

    private int viewCnt;
}
