package com.homubee.jwebcrawler.repository;

import com.homubee.jwebcrawler.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
