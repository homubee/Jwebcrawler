package com.homubee.jwebcrawler.domain;

import com.homubee.jwebcrawler.domain.crawlLog.CrawlLog;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    private String password;

    private String nickname;

    private String refreshToken;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String purpose;

    public List<String> getRoleStringList() {
        return this.getRoleList().stream().map(role -> role.getRoleType().toString()).collect(Collectors.toList());
    }
}
