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
	
	public Comment getPostComment(Integer postId, Integer commentId) {
		Comment comment = commentsApiRepository.findByPostIdAndId(postId, commentId);
		return comment;
	}
	
	public String editPostComment(Integer PostId, Integer CommentId, Comment body) {
		Comment comment = commentsApiRepository.findByPostIdAndId(PostId, CommentId);
		comment.setAuthor(body.getAuthor());
		comment.setBody(body.getBody());
		final Comment updatedComment = commentsApiRepository.save(comment);
		return "OK";
	}
	
	public String deletePostComment(Integer postId, Integer commentId) {
		Comment comment = commentsApiRepository.findByPostIdAndId(postId, commentId);
		commentsApiRepository.delete(comment);
		return "OK";
	}
	
	public Boolean checkIfExists(Integer id) {
		Comment comment = commentsApiRepository.findOne(id);
		if (comment != null) {
			return true;
		}
		else return false;
	}
	
}
