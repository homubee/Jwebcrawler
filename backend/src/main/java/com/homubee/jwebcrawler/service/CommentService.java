package com.homubee.jwebcrawler.service;

import com.homubee.jwebcrawler.domain.Comment;
import com.homubee.jwebcrawler.domain.Post;
import com.homubee.jwebcrawler.dto.request.CommentRequestDTO;
import com.homubee.jwebcrawler.repository.CommentRepository;
import com.homubee.jwebcrawler.repository.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class CommentService {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    PostRepository postRepository;

    public void saveComment(Long postId, CommentRequestDTO requestDTO) {
        Optional<Post> optionalPost = postRepository.findById(postId);
        if (optionalPost.isPresent()) {
            Post post = postRepository.findById(postId).get();
            Comment comment = modelMapper.map(requestDTO, Comment.class);
            comment.setPost(post);
            commentRepository.save(comment);
        }
    }
}
