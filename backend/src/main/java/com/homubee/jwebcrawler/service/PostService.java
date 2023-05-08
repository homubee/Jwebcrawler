package com.homubee.jwebcrawler.service;

import com.homubee.jwebcrawler.domain.Post;
import com.homubee.jwebcrawler.dto.request.PostRequestDTO;
import com.homubee.jwebcrawler.dto.response.PostResponseDTO;
import com.homubee.jwebcrawler.repository.PostRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PostService {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    PostRepository postRepository;

    public void savePost(PostRequestDTO requestDTO) {
        // Save
        Post post = modelMapper.map(requestDTO, Post.class);
        postRepository.save(post);
    }

    public void updatePost(Long postId, PostRequestDTO requestDTO) {
        // Check viewCnt on update
        int viewCnt = 0;
        Optional<Post> currentPost = postRepository.findById(postId);
        if (currentPost.isPresent()) {
            viewCnt = currentPost.get().getViewCnt();
        } else {
            return;
        }

        // Save
        Post post = modelMapper.map(requestDTO, Post.class);
        post.setId(postId);
        post.setViewCnt(viewCnt);
        postRepository.save(post);
    }

    public PostResponseDTO findById(Long postId) {
        // find and increase viewCnt if exists
        Optional<Post> optionalPost = postRepository.findById(postId);
        if (optionalPost.isPresent()) {
            Post post = postRepository.findById(postId).get();
            post.increaseViewCnt();
            postRepository.save(post);
            return modelMapper.map(post, PostResponseDTO.class);
        } else {
            return new PostResponseDTO();
        }
    }

    public List<PostResponseDTO> findPosts() {
        List<Post> posts = postRepository.findAll();
        return modelMapper.map(posts, new TypeToken<List<PostResponseDTO>>(){}.getType());
    }

    public void deletePost(Long postId) {
        // delete if exists
        Optional<Post> optionalPost = postRepository.findById(postId);
        if (optionalPost.isPresent()) {
            Post post = postRepository.findById(postId).get();
            postRepository.delete(post);
        }
    }
}
