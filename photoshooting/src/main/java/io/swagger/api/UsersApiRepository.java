package io.swagger.api;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import io.swagger.model.User;

public interface UsersApiRepository extends CrudRepository<User, Integer>{

	Optional<User> findByUsername(String username);

}
