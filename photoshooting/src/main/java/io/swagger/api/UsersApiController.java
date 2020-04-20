package io.swagger.api;

import io.swagger.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-03-29T16:53:00.885Z[GMT]")
@Controller
public class UsersApiController implements UsersApi {

    private static final Logger log = LoggerFactory.getLogger(UsersApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;
    
    @Autowired
    private UsersApiService usersApiService;

    @org.springframework.beans.factory.annotation.Autowired
    public UsersApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<String> createUser(@ApiParam(value = "User object that needs to be created" ,required=true )  @Valid @RequestBody User body
) {
        if (body.validateAllFields() == true) {
        	String msg = usersApiService.addUser(body);
            return new ResponseEntity<String>(msg, HttpStatus.OK);
        }
        else {
        	return new ResponseEntity<String>("Invalid request", HttpStatus.BAD_REQUEST);        	
        }
    }

    public ResponseEntity<String> deleteUser(@Min(1)@ApiParam(value = "User id",required=true, allowableValues="") @PathVariable("id") Integer id
) {
        if (usersApiService.checkIfExists(id) == true) {
        	String msg = usersApiService.deleteUser(id);
            return new ResponseEntity<String>(msg, HttpStatus.OK);
        }
        else {
        	return new ResponseEntity<String>("User not found", HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<String> editUser(@ApiParam(value = "User object that needs to be updated" ,required=true )  @Valid @RequestBody User body
,@Min(1)@ApiParam(value = "User id",required=true, allowableValues="") @PathVariable("id") Integer id
) {
    	if (body.validateAllFields() == true) {
    		if (usersApiService.checkIfExists(id)) {
    			String msg = usersApiService.updateUser(id,  body);
    	        return new ResponseEntity<String>(msg, HttpStatus.OK);
    		}
    		else {
    			return new ResponseEntity<String>("User not found", HttpStatus.NOT_FOUND);
    		}
    	}
    	else {
    		return new ResponseEntity<String>("Invalid request", HttpStatus.BAD_REQUEST);
    	}
    }

    public ResponseEntity<User> getUser(@Min(1)@ApiParam(value = "User id",required=true, allowableValues="") @PathVariable("id") Integer id
) {
        if (usersApiService.checkIfExists(id) == true) {
        	User user = usersApiService.getUserbyId(id);
        	return new ResponseEntity<User>(user, HttpStatus.OK);
        }
        else {
        	return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<List<User>> getUsers() {
        List<User> users = usersApiService.getUsers();
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

}
