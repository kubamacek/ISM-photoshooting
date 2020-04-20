package io.swagger.api;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.swagger.model.Post;
import io.swagger.model.User;

@Service
public class PostsApiService {
	
	@Autowired
    private PostsApiRepository postsApiRepository;
	
	@Autowired
	private UsersApiService usersApiService;

	public List<Post> getPosts(){
		List<Post> posts = new ArrayList<>();
		postsApiRepository.findAll().forEach(posts::add);
		return posts;
	}
	
	public String addPost(Post post) {
		Post postToSave = new Post();
		postToSave.setTitle(post.getTitle());
		postToSave.setDescription(post.getDescription());
		postToSave.setPhoto(post.getPhoto());
		org.springframework.security.core.userdetails.User user = usersApiService.getCurrentUser().orElseThrow(()->new IllegalArgumentException("No user logged in!"));
		postToSave.setAuthor(user.getUsername());
		postToSave.setDate(Instant.now().toString());
		postsApiRepository.save(postToSave);
		Integer id = postToSave.getId();
		String ids = id.toString();
		return ids;
	}
	
	public Post getPostbyId(Integer id) {
		return postsApiRepository.findOne(id);
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
	}
	
	public String likePost(Integer id) {
		Post post = postsApiRepository.findOne(id);
		Integer likes = post.getLikes();
		likes++;
		post.setLikes(likes);
		postsApiRepository.save(post);
		return "OK";
	}
	
	public Boolean checkIfExists(Integer id) {
		Post post = postsApiRepository.findOne(id);
		if (post != null) {
			return true;
		}
		else return false;
	}
}
