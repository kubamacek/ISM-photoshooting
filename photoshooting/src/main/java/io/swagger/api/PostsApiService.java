package io.swagger.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import io.swagger.model.Post;

@Service
public class PostsApiService {
	
	private List<Post> posts = new ArrayList<>(Arrays.asList(
			new Post(1, 0, "Title1", "Description1", "03-03-2020", "Photo", "John"),
			new Post(2, 0, "Title2", "Description2", "03-03-2020", "Photo", "John"),
			new Post(3, 0, "Title3", "Description3", "03-03-2020", "Photo", "John")
			));
	
	public List<Post> getPosts(){
		return posts;
	}
	
	public Post getPostbyId(long id) {
		return posts.stream().filter(p -> p.getId().equals(id)).findFirst().get();
	}

}
