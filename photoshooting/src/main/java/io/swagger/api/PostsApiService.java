package io.swagger.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.swagger.model.Post;

@Service
public class PostsApiService {
	
	@Autowired
    private PostsApiRepository postsApiRepository;

	public List<Post> getPosts(){
		List<Post> posts = new ArrayList<>();
		postsApiRepository.findAll().forEach(posts::add);
		return posts;
	}
	
	public String addPost(Post post) {
		postsApiRepository.save(post);
		Integer id = post.getId();
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
}
