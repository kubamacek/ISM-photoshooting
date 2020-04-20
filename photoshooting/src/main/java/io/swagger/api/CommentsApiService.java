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
	
	@Autowired
	private UsersApiService usersApiService;
	
	public List<Comment> getPostComments(Integer postId){
		List<Comment> comments = new ArrayList<>();
		commentsApiRepository.findByPostId(postId).forEach(comments::add);
		return comments;
	}
	
	public String addPostComment(Comment comment) {
		Comment commentToSave = new Comment();
		commentToSave.setBody(comment.getBody());
		org.springframework.security.core.userdetails.User user = usersApiService.getCurrentUser().orElseThrow(()->new IllegalArgumentException("No user logged in!"));
		commentToSave.setAuthor(user.getUsername());
		commentToSave.setPost(comment.getPost());
		commentsApiRepository.save(commentToSave);
		Integer id = commentToSave.getId();
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
