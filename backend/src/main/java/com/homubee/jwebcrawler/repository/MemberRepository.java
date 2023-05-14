package com.homubee.jwebcrawler.repository;

import com.homubee.jwebcrawler.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findByEmail(String email);
}
