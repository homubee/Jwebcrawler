package com.homubee.jwebcralwer.repository;

import com.homubee.jwebcralwer.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
