package io.swagger.api;

import org.springframework.data.repository.CrudRepository;

import io.swagger.model.Post;

public interface PostsApiRepository extends CrudRepository<Post, Integer> {

}
