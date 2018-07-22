package com.kimtis.blog.service;

import com.kimtis.blog.model.Post;
import com.kimtis.blog.model.response.ListPost;
import com.kimtis.blog.repository.PostJdbcRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ListPostService {
	private final PostJdbcRepository postRepository;

	public ListPost page(long page, long size) {
		List<Post> posts = postRepository.findNextPage(page, size);
		long total = postRepository.count();
		return ListPost.builder()
			.page(page)
			.size(size)
			.total(total)
			.posts(posts)
			.build();
	}
}
