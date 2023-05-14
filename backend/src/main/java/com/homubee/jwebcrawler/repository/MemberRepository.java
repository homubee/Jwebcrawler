package com.homubee.jwebcrawler.repository;

import com.homubee.jwebcrawler.domain.Member;
import com.homubee.jwebcrawler.repository.custom.CustomMemberRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long>, CustomMemberRepository {
    List<Member> findByEmail(String email);
}
