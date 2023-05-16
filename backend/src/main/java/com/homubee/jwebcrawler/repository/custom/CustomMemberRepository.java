package com.homubee.jwebcrawler.repository.custom;

import com.homubee.jwebcrawler.domain.Member;
import com.homubee.jwebcrawler.domain.MemberSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomMemberRepository {
    public Page<Member> search(MemberSearch memberSearch, Pageable pageable);
}
