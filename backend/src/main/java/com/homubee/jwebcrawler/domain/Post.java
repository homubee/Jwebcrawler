package com.homubee.jwebcrawler.domain;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

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
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post extends BaseEntity {
    @Id
    @GeneratedValue(generator = "POST_SEQ_GENERATOR")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="MEMBER_ID")
    private Member member;

    @OneToMany
    @JoinColumn(name = "POST_ID")
    @Builder.Default
    private List<Comment> commentList = new ArrayList<>();

    private String title;

    private String content;

    @ColumnDefault("0")
    private int viewCnt;

    public void increaseViewCnt() {
        this.viewCnt++;
    }
}
