package io.swagger.api;

import io.swagger.model.Meeting;
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
public class MeetingsApiController implements MeetingsApi {

    private static final Logger log = LoggerFactory.getLogger(MeetingsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;
    
    @Autowired
    private MeetingsApiService meetingsApiService;

    @org.springframework.beans.factory.annotation.Autowired
    public MeetingsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<String> createMeeting(@ApiParam(value = "Meeting object that needs to be created" ,required=true )  @Valid @RequestBody Meeting body
) {
        if (body.validateAllFields() == true) {
        	String msg = meetingsApiService.addMeeting(body);
            return new ResponseEntity<String>(msg, HttpStatus.OK);
        }
        else {
        	return new ResponseEntity<String>("Invalid request", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> deleteMeeting(@Min(1)@ApiParam(value = "Meeting id",required=true, allowableValues="") @PathVariable("id") Integer id
) {
    	if (meetingsApiService.checkIfExists(id) == true) {
            String msg = meetingsApiService.deleteMeeting(id);
            return new ResponseEntity<String>(msg, HttpStatus.OK);
    	}
    	else {
            return new ResponseEntity<String>("Meeting not found", HttpStatus.NOT_FOUND);
    	}
    }

    public ResponseEntity<String> editMeeting(@ApiParam(value = "Meeting object that needs to be created" ,required=true )  @Valid @RequestBody Meeting body
,@Min(1)@ApiParam(value = "Meeting id",required=true, allowableValues="") @PathVariable("id") Integer id
) {
    	if (body.validateAllFields() == true) {
    		if (meetingsApiService.checkIfExists(id) == true) {
    	        String msg = meetingsApiService.updateMeeting(id, body);
    	        return new ResponseEntity<String>(msg, HttpStatus.OK);
    		}
    		else {
    	        return new ResponseEntity<String>("Meeting not found", HttpStatus.NOT_FOUND);    		}
    	}
    	else {
            return new ResponseEntity<String>("Invalid request", HttpStatus.BAD_REQUEST);
    	}
    }

    public ResponseEntity<Meeting> getMeeting(@Min(1)@ApiParam(value = "Meeting id",required=true, allowableValues="") @PathVariable("id") Integer id
) {
        if (meetingsApiService.checkIfExists(id) == true) {
            Meeting meeting = meetingsApiService.getMeetingbyId(id);
            return new ResponseEntity<Meeting>(meeting, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<Meeting>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<List<Meeting>> getMeetings() {
        List<Meeting> meetings = meetingsApiService.getMeetings();
        return new ResponseEntity<List<Meeting>>(meetings, HttpStatus.OK);
    }

}
