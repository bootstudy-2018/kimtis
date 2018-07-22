package com.kimtis.blog.service;

import com.kimtis.blog.model.Post;
import com.kimtis.blog.model.response.DetailPost;
import com.kimtis.blog.repository.PostJdbcRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DetailPostService {
	private final PostJdbcRepository postRepository;

	public DetailPost findOne(long id) {
		Post post = postRepository.findById(id);
		return DetailPost.builder()
			.postId(post.getId())
			.title(post.getTitle())
			.content(post.getContent())
			.author(post.getAuthor())
			.createdAt(post.getCreatedAt())
			.updatedAt(post.getUpdatedAt())
			.hit(post.getHit())
			.build();
	}
}
