package io.swagger.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import io.swagger.model.LoginRequest;
import io.swagger.model.Post;
import io.swagger.model.User;

@Service
public class UsersApiService {
	
	@Autowired
	private UsersApiRepository usersApiRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtProvider jwtProvider;
	
	private String encodePassword(String password) {
		return passwordEncoder.encode(password);
	}
	
	public List<User> getUsers(){
		List<User> users = new ArrayList<>();
		usersApiRepository.findAll().forEach(users::add);
		return users;
	}
	
	public String addUser(User user) {
		user.setPassword(encodePassword(user.getPassword()));
		usersApiRepository.save(user);
		Integer id = user.getId();
		String ids = id.toString();
		return ids;
	}
	
	public User getUserbyId(Integer id) {
		return usersApiRepository.findOne(id);
	}
	
	public String updateUser(Integer id, User body) {
		User user = usersApiRepository.findOne(id);
		user.setEmail(body.getEmail());
		user.setFirstName(body.getFirstName());
		user.setLastName(body.getLastName());
		user.setPassword(body.getPassword());
		user.setPhone(body.getPhone());
		user.setUsername(body.getUsername());
		final User updatedUser = usersApiRepository.save(user);
		return "OK";
	}
	
	public String deleteUser(Integer id) {
		usersApiRepository.delete(id);
		return "OK";
	}
	
	public Boolean checkIfExists(Integer id) {
		User user = usersApiRepository.findOne(id);
		if (user != null) {
			return true;
		}
		else return false;
	}
	
	public String login(LoginRequest loginRequest) {
		Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authenticate);
		return jwtProvider.generateToken(authenticate);
	}
	
	public Optional<org.springframework.security.core.userdetails.User> getCurrentUser() {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.
                getContext().getAuthentication().getPrincipal();
        return Optional.of(principal);
    }


}
