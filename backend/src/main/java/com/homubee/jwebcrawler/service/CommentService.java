package com.homubee.jwebcrawler.service;

import com.homubee.jwebcrawler.domain.Comment;
import com.homubee.jwebcrawler.domain.Member;
import com.homubee.jwebcrawler.domain.Post;
import com.homubee.jwebcrawler.dto.request.CommentRequestDTO;
import com.homubee.jwebcrawler.repository.CommentRepository;
import com.homubee.jwebcrawler.repository.MemberRepository;
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
    @Autowired
    MemberRepository memberRepository;

    public void saveComment(CommentRequestDTO requestDTO) {
        Long memberId = requestDTO.getMemberId();
        Long postId = requestDTO.getPostId();
        Optional<Member> optionalMember = memberRepository.findById(memberId);
        Optional<Post> optionalPost = postRepository.findById(postId);

        // check memberId and postId
        if (optionalPost.isPresent() && optionalMember.isPresent()) {
            Member member = optionalMember.get();
            Post post = optionalPost.get();
            Comment comment = Comment.builder()
                    .member(member)
                    .post(post)
                    .content(requestDTO.getContent())
                    .build();
            commentRepository.save(comment);
        }
    }
}
