package com.homubee.jwebcrawler.repository.custom;

import com.homubee.jwebcrawler.domain.Post;
import com.homubee.jwebcrawler.domain.PostSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomPostRepository {
    public Page<Post> search(PostSearch postSearch, Pageable pageable);
}
