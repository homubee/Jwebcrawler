package com.homubee.jwebcralwer.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="MEMBER")
@SequenceGenerator(
        name = "MEMBER_SEQ_GENERATOR",
        sequenceName = "MEMBER_SEQ",
        initialValue = 1, allocationSize = 1)
@Getter
@Setter
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(generator = "MEMBER_SEQ_GENERATOR")
    private Long id;

    @OneToMany
    @JoinColumn(name = "MEMBER_ID")
    private List<Role> roleList = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "MEMBER_ID")
    private List<CrawlLog> crawlLogList = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "MEMBER_ID")
    private List<Post> postList = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "MEMBER_ID")
    private List<Comment> commentList = new ArrayList<>();

    private String email;

    private String nickname;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String purpose;
}
