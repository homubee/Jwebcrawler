package com.homubee.jwebcrawler.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="ROLE")
@SequenceGenerator(
        name = "ROLE_SEQ_GENERATOR",
        sequenceName = "ROLE_SEQ",
        initialValue = 1, allocationSize = 1)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Role extends BaseEntity {
    @Id
    @GeneratedValue(generator = "ROLE_SEQ_GENERATOR")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="MEMBER_ID")
    private Member member;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;
}
