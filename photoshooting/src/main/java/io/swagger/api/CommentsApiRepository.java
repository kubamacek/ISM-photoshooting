package io.swagger.api;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import io.swagger.model.Comment;
import io.swagger.model.Post;

@Repository
public interface CommentsApiRepository extends CrudRepository<Comment, Integer> {
	
	public List<Comment> findByPostId(Integer postId);

}
