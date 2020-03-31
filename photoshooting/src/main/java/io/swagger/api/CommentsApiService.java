package io.swagger.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.swagger.model.Comment;
import io.swagger.model.Post;

@Service
public class CommentsApiService {

	@Autowired
	private CommentsApiRepository commentsApiRepository;
	
	public List<Comment> getPostComments(Integer postId){
		List<Comment> comments = new ArrayList<>();
		commentsApiRepository.findByPostId(postId).forEach(comments::add);
		return comments;
	}
	
	public String addPostComment(Comment comment) {
		commentsApiRepository.save(comment);
		Integer id = comment.getId();
		String ids = id.toString();
		return ids;
	}
	
	/*public Post getPostComment(Integer postId, Integer commentId) {
		commentsApiRepository.findByPostId(postId)
	}
	
	public String updatePost(Integer id, Post body) {
		Post post = postsApiRepository.findOne(id);
		post.setAuthor(body.getAuthor());
		post.setDate(body.getDate());
		post.setDescription(body.getDescription());
		post.setTitle(body.getTitle());
		final Post updatedPost = postsApiRepository.save(post);
		return "OK";
	}
	
	public String deletePost(Integer id) {
		postsApiRepository.delete(id);
		return "OK";
	}*/
	
}
