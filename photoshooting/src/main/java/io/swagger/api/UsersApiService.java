package io.swagger.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersApiService {
	
	@Autowired
	private UsersApiRepository usersApiRepository;

}
