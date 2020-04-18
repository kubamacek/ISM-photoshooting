package io.swagger.api;

import org.springframework.data.repository.CrudRepository;

import io.swagger.model.User;

public interface UsersApiRepository extends CrudRepository<User, Integer>{

}
