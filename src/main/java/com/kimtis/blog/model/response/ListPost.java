package com.kimtis.blog.model.response;

import com.kimtis.blog.model.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class ListPost {
	private Long page;
	private Long size;
	private Long total;
	private List<Post> posts;
}
