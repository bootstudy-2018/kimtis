package com.kimtis.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.sql.Date;

@Data
@Builder
@AllArgsConstructor
public class Post {
	private Long id;
	private String title;
	private String content;
	private String author;
	private Date createdAt;
	private Date updatedAt;
	private Long hit;
}
