package com.kimtis.blog.repository;

import com.kimtis.blog.model.Post;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PostJdbcRepository {
	private final NamedParameterJdbcTemplate jdbcTemplate;

	private static final String QUERY_COUNT
		= "SELECT COUNT(*)"
		+ " FROM POST";

	private static final String QUERY_READ_PAGE
		= "SELECT *"
		+ " FROM POST"
		+ " ORDER BY ID DESC"
		+ " LIMIT :page * :size, :size";

	private static final String QUERY_READ_ONE
		= "SELECT *"
		+ " FROM POST"
		+ " WHERE ID=:id LIMIT 1";

	private static final String QUERY_CREATE
		= "INSERT INTO POST (TITLE, CONTENT, AUTHOR, CREATED_AT, UPDATED_AT, HIT)"
		+ " VALUES (:title, :content, :author, :createdAt, updatedAt, hit)";

	public long count() {
		return jdbcTemplate.queryForObject(QUERY_COUNT, Collections.emptyMap(),
			(rs, rowNum) -> rs.getLong(1)
		);
	}

	public List<Post> findNextPage(Long page, Long size) {
		SqlParameterSource parameterSource = new MapSqlParameterSource()
			.addValue("page", page)
			.addValue("size", size);

		return jdbcTemplate.query(QUERY_READ_PAGE, parameterSource,
			(rs, rowNum) -> Post.builder()
				.id(rs.getLong("ID"))
				.title(rs.getString("TITLE"))
				.content(rs.getString("CONTENT"))
				.author(rs.getString("AUTHOR"))
				.createdAt(rs.getDate("CREATED_AT"))
				.updatedAt(rs.getDate("UPDATED_AT"))
				.hit(rs.getLong("HIT"))
				.build()
		);
	}

	public Post findById(Long id) {
		SqlParameterSource parameterSource = new MapSqlParameterSource()
			.addValue("id", id);

		return jdbcTemplate.queryForObject(QUERY_READ_ONE, parameterSource,
			(rs, rowNum) -> Post.builder()
				.id(rs.getLong("ID"))
				.title(rs.getString("TITLE"))
				.content(rs.getString("CONTENT"))
				.author(rs.getString("AUTHOR"))
				.createdAt(rs.getDate("CREATED_AT"))
				.updatedAt(rs.getDate("UPDATED_AT"))
				.hit(rs.getLong("HIT"))
				.build()
		);
	}

	public Post save(Post post) {
		SqlParameterSource parameterSource = new MapSqlParameterSource()
			.addValue("title", post.getTitle())
			.addValue("content", post.getContent())
			.addValue("author", post.getAuthor())
			.addValue("createdAt", post.getCreatedAt())
			.addValue("updatedAt", post.getUpdatedAt())
			.addValue("hit", post.getHit());

		KeyHolder keyHolder = new GeneratedKeyHolder();
		int resultCode = jdbcTemplate.update(QUERY_CREATE, parameterSource, keyHolder, new String[] { "ID" });

		return (resultCode == 0) ? null : Post.builder()
			.id(keyHolder.getKey().longValue())
			.title(post.getTitle())
			.content(post.getContent())
			.author(post.getAuthor())
			.createdAt(post.getCreatedAt())
			.updatedAt(post.getUpdatedAt())
			.hit(post.getHit())
			.build();
	}
}
