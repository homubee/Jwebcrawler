package com.homubee.jwebcrawler.repository;

import com.homubee.jwebcrawler.domain.Post;
import com.homubee.jwebcrawler.repository.custom.CustomPostRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long>, CustomPostRepository {
}
