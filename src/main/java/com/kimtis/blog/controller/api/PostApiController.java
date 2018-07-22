package com.kimtis.blog.controller.api;

import com.kimtis.blog.model.Post;
import com.kimtis.blog.repository.PostJdbcRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.util.List;

@Validated
@RestController
@RequestMapping(value = "/api/v1/post")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PostApiController {
	private final PostJdbcRepository postRepository;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<Post> posts(
		@Min(0) @RequestParam(defaultValue = "0") Long page,
		@Min(1) @RequestParam(defaultValue = "5") Long size) {
		return postRepository.findNextPage(page, size);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Post post(
		@Min(0) @PathVariable Long id) {
		return postRepository.findById(id);
	}
}
