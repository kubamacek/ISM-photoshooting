package io.swagger.api;

import org.springframework.data.repository.CrudRepository;

import io.swagger.model.Meeting;

public interface MeetingsApiRepository extends CrudRepository<Meeting, Integer>{

}
