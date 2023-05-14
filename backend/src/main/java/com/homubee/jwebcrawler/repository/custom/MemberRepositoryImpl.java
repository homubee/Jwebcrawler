package com.homubee.jwebcrawler.repository.custom;

import com.homubee.jwebcrawler.domain.Member;
import com.homubee.jwebcrawler.domain.MemberSearch;
import com.homubee.jwebcrawler.domain.QMember;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.util.StringUtils;

import java.util.List;

public class MemberRepositoryImpl extends QuerydslRepositorySupport implements CustomMemberRepository {
    public MemberRepositoryImpl() {
        super(Member.class);
    }

    @Override
    public List<Member> search(MemberSearch memberSearch) {
        QMember member = QMember.member;

        JPQLQuery query = from(member);

        if (StringUtils.hasText(memberSearch.getEmail())) {
            query.where(member.email.contains(memberSearch.getEmail()));
        }

        if (StringUtils.hasText(memberSearch.getNickname())) {
            query.where(member.nickname.contains(memberSearch.getNickname()));
        }

        if (memberSearch.getGender() != null) {
            query.where(member.gender.eq(memberSearch.getGender()));
        }

        return query.fetch();
    }
}
