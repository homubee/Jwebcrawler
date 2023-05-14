package com.homubee.jwebcrawler.security;

import com.homubee.jwebcrawler.domain.Member;
import com.homubee.jwebcrawler.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<Member> findMembers = memberRepository.findByEmail(username);
        if (findMembers.isEmpty()) {
            throw new UsernameNotFoundException(username);
        }

        CustomUserDetail userDetail = new CustomUserDetail(findMembers.get(0));
        return userDetail;
    }
}
