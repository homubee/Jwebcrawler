package com.homubee.jwebcrawler.repository.custom;

import com.homubee.jwebcrawler.domain.Member;
import com.homubee.jwebcrawler.domain.MemberSearch;
import com.homubee.jwebcrawler.domain.QMember;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.util.StringUtils;

import java.util.List;

public class MemberRepositoryImpl extends QuerydslRepositorySupport implements CustomMemberRepository {
    public MemberRepositoryImpl() {
        super(Member.class);
    }

    @Override
    public Page<Member> search(MemberSearch memberSearch, Pageable pageable) {
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

        query.offset(pageable.getOffset());
        query.limit(pageable.getPageSize());
        for (Sort.Order order : pageable.getSort()) {
            PathBuilder pathBuilder;
            if (order.getProperty().equals("email") || order.getProperty().equals("nickname")) {
                pathBuilder = new PathBuilder(member.getType(), member.getMetadata());
            } else {
                continue;
            }
            System.out.println("=========== PATH : " + pathBuilder.get(order.getProperty()));
            query.orderBy(new OrderSpecifier<>(order.isAscending() ? Order.ASC : Order.DESC,
                    pathBuilder.get(order.getProperty())));
        }

        List<Member> result = query.fetch();
        int count = result.size();

        return new PageImpl<>(result, pageable, count);
    }
}
