package com.homubee.jwebcrawler.repository.custom;

import com.homubee.jwebcrawler.domain.*;
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

public class PostRepositoryImpl extends QuerydslRepositorySupport implements CustomPostRepository {
    public PostRepositoryImpl() {
        super(Post.class);
    }

    @Override
    public Page<Post> search(PostSearch postSearch, Pageable pageable) {
        QPost post = QPost.post;
        QMember member = QMember.member;

        JPQLQuery query = from(post);

        if (StringUtils.hasText(postSearch.getTitle())) {
            query.where(post.title.contains(postSearch.getTitle()));
        }

        if (StringUtils.hasText(postSearch.getContent())) {
            query.where(post.content.contains(postSearch.getContent()));
        }

        if (StringUtils.hasText(postSearch.getMemberEmail())) {
            query.leftJoin(post.member, member).where(member.email.contains(postSearch.getMemberEmail()));
        }

        if (StringUtils.hasText(postSearch.getMemberNickname())) {
            query.leftJoin(post.member, member).where(member.nickname.contains(postSearch.getMemberNickname()));
        }

        query.offset(pageable.getOffset());
        query.limit(pageable.getPageSize());
        for (Sort.Order order : pageable.getSort()) {
            PathBuilder pathBuilder;

            if (order.getProperty().equals("title") || order.getProperty().equals("content")) {
                pathBuilder = new PathBuilder(post.getType(), post.getMetadata());
            } else if (order.getProperty().equals("email") || order.getProperty().equals("nickname")) {
                pathBuilder = new PathBuilder(member.getType(), member.getMetadata());
            } else {
                continue;
            }
            query.leftJoin(post.member, member).orderBy(new OrderSpecifier<>(order.isAscending() ? Order.ASC : Order.DESC,
                    pathBuilder.get(order.getProperty())));
        }

        List<Post> result = query.fetch();
        int count = result.size();

        return new PageImpl<>(result, pageable, count);
    }
}
