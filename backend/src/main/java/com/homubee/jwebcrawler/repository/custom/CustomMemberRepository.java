package com.homubee.jwebcrawler.repository.custom;

import com.homubee.jwebcrawler.domain.Member;
import com.homubee.jwebcrawler.domain.MemberSearch;

import java.util.List;

public interface CustomMemberRepository {
    public List<Member> search(MemberSearch memberSearch);
}
