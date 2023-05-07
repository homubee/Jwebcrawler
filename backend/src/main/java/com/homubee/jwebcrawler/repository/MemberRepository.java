package com.homubee.jwebcrawler.repository;

import com.homubee.jwebcrawler.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
