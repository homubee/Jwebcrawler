package com.homubee.jwebcrawler.service;

import com.homubee.jwebcrawler.domain.Member;
import com.homubee.jwebcrawler.domain.Post;
import com.homubee.jwebcrawler.domain.PostSearch;
import com.homubee.jwebcrawler.dto.request.PostRequestDTO;
import com.homubee.jwebcrawler.dto.response.PostResponseDTO;
import com.homubee.jwebcrawler.repository.MemberRepository;
import com.homubee.jwebcrawler.repository.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class PostService {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    PostRepository postRepository;
    @Autowired
    MemberRepository memberRepository;

    public void savePost(PostRequestDTO requestDTO) {

        Long memberId = requestDTO.getMemberId();
        Optional<Member> optionalMember = memberRepository.findById(requestDTO.getMemberId());
        if (optionalMember.isPresent()) {
            Member member = memberRepository.findById(memberId).get();
            // Save
            Post post = Post.builder()
                    .member(member)
                    .title(requestDTO.getTitle())
                    .content(requestDTO.getContent())
                    .build();
            postRepository.save(post);
        }
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

        Long memberId = requestDTO.getMemberId();
        Optional<Member> optionalMember = memberRepository.findById(requestDTO.getMemberId());
        if (optionalMember.isPresent()) {
            Member member = memberRepository.findById(memberId).get();
            // Save
            Post post = Post.builder()
                    .id(postId)
                    .member(member)
                    .title(requestDTO.getTitle())
                    .content(requestDTO.getContent())
                    .viewCnt(viewCnt)
                    .build();
            postRepository.save(post);
        }
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

    public Page<PostResponseDTO> findPosts(PostSearch postSearch, Pageable pageable) {
        Page<Post> posts = postRepository.search(postSearch, pageable);
        return posts.map(post -> modelMapper.map(post, PostResponseDTO.class));
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
