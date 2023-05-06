package com.homubee.jwebcralwer.repository;

import com.homubee.jwebcralwer.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
