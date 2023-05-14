package com.homubee.jwebcrawler.security;

import com.homubee.jwebcrawler.domain.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class CustomUserDetail implements UserDetails {
    private String username;
    private String password;
    ArrayList<GrantedAuthority> authList = new ArrayList<>();

    public CustomUserDetail(Member member) {
        this.username = member.getEmail();
        this.password = member.getPassword();
        member.getRoleList().stream().forEach(role -> {
            authList.add(new SimpleGrantedAuthority(role.getRoleType().toString()));
        });
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authList;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
